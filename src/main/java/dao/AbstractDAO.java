package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * Class that handles the database operations for a generic entity using reflection to create the queries and to obtain the data from the database in table form
 * @param <T> the type of the entity
 * @author Adrian Baraian
 */
public class AbstractDAO<T> {
    /**
     * The logger for the current class
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    /**
     * The type of the entity
     */
    private final Class<T> type;


    /**
     * Constructor that initializes the type of the entity
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Method that creates a SELECT query for the entity
     * @param field the field to be selected
     * @return the query
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM \"");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append("\"");
        if(!Objects.equals(field, "*")) {
            sb.append(" WHERE " + field + " =?");
        }

        //System.out.println(sb.toString());

        return sb.toString();
    }

    /**
     * Method that creates an INSERT query for the entity
     * @return the query
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO \"");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append("\" ( ");
        for(Field f : type.getDeclaredFields()) {
            if(Objects.equals(f.getName(), "id")) {
                continue;
            }
            sb.append(f.getName());
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(") ");
        sb.append("VALUES ( ");
        for (Field f : type.getDeclaredFields()) {
            if(Objects.equals(f.getName(), "id")) {
                continue;
            }
            sb.append("?, ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(" )");

        //System.out.println(sb.toString());

        return sb.toString();
    }

    /**
     * Method that creates an UPDATE query for the entity
     * @param id the id of the entity to be updated
     * @return the query
     */
    private String createUpdateQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE \"");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append("\" SET ");
        for(Field f : type.getDeclaredFields()) {
            if(Objects.equals(f.getName(), "id")) {
                continue;
            }
            sb.append(f.getName());
            sb.append(" = ?, ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append(" WHERE id = " + id);

        //System.out.println(sb.toString());

        return sb.toString();
    }

    /**
     * Method that creates a DELETE query for the entity
     * @param id the id of the entity to be deleted
     * @return the query
     */
    private String createDeleteQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id = " + id);

        System.out.println(sb);


        return sb.toString();
    }

    /**
     * Method that finds all the entities of the type T
     * @return a list with all the entities
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = createSelectQuery("*");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
            //e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Method that finds an entity by its id
     * @param id the id of the entity
     * @return the entity with the given id
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
            //e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ID not found");
        }
        finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Method that creates a list of entities from a ResultSet
     * @param resultSet the ResultSet from which the entities are created
     * @return the list of entities
     */
    protected List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    //System.out.println(method.getName() + " " + value);
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Method that creates an entity from a string array
     * @param values the string array from which the entity is created
     * @return the entity
     */
    public T createObject(String[] values) {
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            ctor.setAccessible(true);
            T instance = (T)ctor.newInstance();
            int i = 0;
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                String value = values[i++];
                Object convertedValue = convertValue(value, fieldType);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(instance, convertedValue);
            }
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method that converts a string value to a value of a given type
     * @param value the string value
     * @param type the type to which the value is converted
     * @return the converted value
     */
    private Object convertValue(String value, Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == long.class || type == Long.class) {
            return Long.parseLong(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == float.class || type == Float.class) {
            return Float.parseFloat(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            return value;
        }
    }


    /**
     * Method that inserts an entity in the database
     * @param t the entity to be inserted
     * @return the id of the inserted entity
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        int insertedID = -1;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int fieldNumber = 1;
            for(Field f : type.getDeclaredFields()) {
                if(Objects.equals(f.getName(), "id")) {
                    continue;
                }
                f.setAccessible(true);
                statement.setObject(fieldNumber++, f.get(t));
            }

            //System.out.println(statement.toString());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                insertedID = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
            //e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return insertedID;
    }

    /**
     * Method that updates an entity in the database
     * @param t the entity to be updated
     * @return the id of the updated entity
     */
    public int update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int insertedID = -1;

        int id = -1;
        for (Field f : type.getDeclaredFields()) {
            if (Objects.equals(f.getName(), "id")) {
                try {
                    f.setAccessible(true);
                    id = (int) f.get(t);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        String query = createUpdateQuery(id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int fieldNumber = 1;
            for(Field f : type.getDeclaredFields()) {
                if (Objects.equals(f.getName(), "id")) {
                    continue;
                }
                f.setAccessible(true);
                statement.setObject(fieldNumber++, f.get(t));
            }

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                insertedID = resultSet.getInt(1);
            }

        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return insertedID;
    }

    /**
     * Method that deletes an entity from the database
     * @param id the id of the entity to be deleted
     */
    public void delete (int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        String query = createDeleteQuery(id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.executeUpdate();

        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
            //e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Method that returns the header of the table for the entity
     * @return the header of the table
     */
    public String[] getTableHeader() {
        String[] columnName = new String[type.getDeclaredFields().length];
        int i = 0;
        for (var field : type.getDeclaredFields()) {
            columnName[i] = field.getName();
            i++;
        }

        return columnName;
    }

    /**
     * Method that returns the data of the table for the entity
     * @return the data of the table
     */
    public String[][] getTableData() {
        List<T> list = this.findAll();

        String[][] data = new String[list.size()][type.getDeclaredFields().length];

        for(int i = 0; i < list.size(); i++) {
            int j = 0;
            for (Field field : type.getDeclaredFields()) {
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    data[i][j++] = propertyDescriptor.getReadMethod().invoke(list.get(i)).toString();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IntrospectionException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return data;
    }
}
