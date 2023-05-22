package Model.Dao;

import DatabaseConnection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    private final Class<T> type;
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private List<T> createObjects(ResultSet resultSet) {
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
                assert ctor != null;
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalArgumentException | SecurityException | IllegalAccessException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

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
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createUpdateQuery(String field, int id){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + type.getSimpleName() + " SET " + field + " =? WHERE id = " + id);
        return sb.toString();
    }

    public T update(String f, String f1, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(f, id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, f1);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(");

        Field[] allFields = t.getClass().getDeclaredFields();
        StringBuilder values = new StringBuilder();

        try {
            for(int i = 0; i < allFields.length - 1; i ++){
                allFields[i].setAccessible(true);

                sb.append(allFields[i].getName() + ", ");
                Object obj = allFields[i].get(t);

                String fieldType = allFields[i].getType().getSimpleName();
                if (fieldType.equals("String"))
                    values.append("\"").append(obj).append("\"");
                else
                    values.append(obj);
                values.append(", ");

            }

            allFields[allFields.length - 1].setAccessible(true);
            sb.append(allFields[allFields.length - 1].getName());
            Object value = allFields[allFields.length - 1].get(t);
            String fieldType = allFields[allFields.length - 1].getType().getSimpleName();

            if (fieldType.equals("String"))
                values.append("\"").append(value).append("\"");
            else
                values.append(value);

            sb.append(") VALUES (").append(values).append(")");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    private String createDeleteQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + type.getSimpleName() + " WHERE id = ?");
        return sb.toString();
    }

    public T delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}
