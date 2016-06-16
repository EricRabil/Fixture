package com.ericrabil.fixture.database.db;


import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.math.*;

public class LoggingPreparedStatement implements PreparedStatement
{
    private PreparedStatement delegate;
    private String sql;
    private LoggingConnection loggingConn;

    public LoggingPreparedStatement(PreparedStatement delegate, String sql, LoggingConnection loggingConn)
    {
        this.delegate = delegate;
        this.sql = sql;
        this.loggingConn = loggingConn;
    }

    @Override
    public void addBatch()
    throws SQLException
    {
        delegate.addBatch();
    }

    @Override
    public void clearParameters()
    throws SQLException
    {
        delegate.clearParameters();
    }

    @Override
    public boolean execute()
    throws SQLException
    {
        long time = System.currentTimeMillis();
        boolean result = delegate.execute();
        time = System.currentTimeMillis() - time;
        loggingConn.updateEntry(sql, time);
        return result;
    }

    @Override
    public ResultSet executeQuery()
    throws SQLException
    {
        return delegate.executeQuery();
    }

    @Override
    public int executeUpdate()
    throws SQLException
    {
        return delegate.executeUpdate();
    }

    @Override
    public ResultSetMetaData getMetaData()
    throws SQLException
    {
        return delegate.getMetaData();
    }

    @Override
    public ParameterMetaData getParameterMetaData()
    throws SQLException
    {
        return delegate.getParameterMetaData();
    }

    @Override
    public void setArray(int parameterIndex, Array x)
    throws SQLException
    {
        delegate.setArray(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x)
    throws SQLException
    {
        delegate.setAsciiStream(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length)
    throws SQLException
    {
        delegate.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length)
    throws SQLException
    {
        delegate.setAsciiStream(parameterIndex, x, length);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x)
    throws SQLException
    {
        delegate.setBigDecimal(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x)
    throws SQLException
    {
        delegate.setBinaryStream(parameterIndex, x);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length)
    throws SQLException
    {
        delegate.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length)
    throws SQLException
    {
        delegate.setBinaryStream(parameterIndex, x, length);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x)
    throws SQLException
    {
        delegate.setBlob(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream)
    throws SQLException
    {
        delegate.setBlob(parameterIndex, inputStream);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length)
    throws SQLException
    {
        delegate.setBlob(parameterIndex, inputStream, length);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x)
    throws SQLException
    {
        delegate.setBoolean(parameterIndex, x);
    }

    @Override
    public void setByte(int parameterIndex, byte x)
    throws SQLException
    {
        delegate.setByte(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x)
    throws SQLException
    {
        delegate.setBytes(parameterIndex, x);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader)
    throws SQLException
    {
        delegate.setCharacterStream(parameterIndex, reader);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length)
    throws SQLException
    {
        delegate.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length)
    throws SQLException
    {
        delegate.setCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setClob(int parameterIndex, Clob x)
    throws SQLException
    {
        delegate.setClob(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader)
    throws SQLException
    {
        delegate.setClob(parameterIndex, reader);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length)
    throws SQLException
    {
        delegate.setClob(parameterIndex, reader, length);
    }

    @Override
    public void setDate(int parameterIndex, Date x)
    throws SQLException
    {
        delegate.setDate(parameterIndex, x);
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal)
    throws SQLException
    {
        delegate.setDate(parameterIndex, x, cal);
    }

    @Override
    public void setDouble(int parameterIndex, double x)
    throws SQLException
    {
        delegate.setDouble(parameterIndex, x);
    }

    @Override
    public void setFloat(int parameterIndex, float x)
    throws SQLException
    {
        delegate.setFloat(parameterIndex, x);
    }

    @Override
    public void setInt(int parameterIndex, int x)
    throws SQLException
    {
        delegate.setInt(parameterIndex, x);
    }

    @Override
    public void setLong(int parameterIndex, long x)
    throws SQLException
    {
        delegate.setLong(parameterIndex, x);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value)
    throws SQLException
    {
        delegate.setNCharacterStream(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length)
    throws SQLException
    {
        delegate.setNCharacterStream(parameterIndex, value, length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value)
    throws SQLException
    {
        delegate.setNClob(parameterIndex, value);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader)
    throws SQLException
    {
        delegate.setNClob(parameterIndex, reader);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length)
    throws SQLException
    {
        delegate.setNClob(parameterIndex, reader, length);
    }

    @Override
    public void setNString(int parameterIndex, String value)
    throws SQLException
    {
        delegate.setNString(parameterIndex, value);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType)
    throws SQLException
    {
        delegate.setNull(parameterIndex, sqlType);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName)
    throws SQLException
    {
        delegate.setNull(parameterIndex, sqlType, typeName);
    }

    @Override
    public void setObject(int parameterIndex, Object x)
    throws SQLException
    {
        delegate.setObject(parameterIndex, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType)
    throws SQLException
    {
        delegate.setObject(parameterIndex, x, targetSqlType);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength)
    throws SQLException
    {
        delegate.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setRef(int parameterIndex, Ref x)
    throws SQLException
    {
        delegate.setRef(parameterIndex, x);
    }

    @Override
    public void setRowId(int parameterIndex, RowId x)
    throws SQLException
    {
        delegate.setRowId(parameterIndex, x);
    }

    @Override
    public void setShort(int parameterIndex, short x)
    throws SQLException
    {
        delegate.setShort(parameterIndex, x);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject)
    throws SQLException
    {
        delegate.setSQLXML(parameterIndex, xmlObject);
    }

    @Override
    public void setString(int parameterIndex, String x)
    throws SQLException
    {
        delegate.setString(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x)
    throws SQLException
    {
        delegate.setTime(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal)
    throws SQLException
    {
        delegate.setTime(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x)
    throws SQLException
    {
        delegate.setTimestamp(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
    throws SQLException
    {
        delegate.setTimestamp(parameterIndex, x, cal);
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length)
    throws SQLException
    {
        delegate.setUnicodeStream(parameterIndex, x, length);
    }

    @Override
    public void setURL(int parameterIndex, URL x)
    throws SQLException
    {
        delegate.setURL(parameterIndex, x);
    }

    @Override
    public void addBatch(String sql)
    throws SQLException
    {
        delegate.addBatch(sql);
    }

    @Override
    public void cancel()
    throws SQLException
    {
        delegate.cancel();
    }

    @Override
    public void clearBatch()
    throws SQLException
    {
        delegate.clearBatch();
    }

    @Override
    public void clearWarnings()
    throws SQLException
    {
        delegate.clearWarnings();
    }

    @Override
    public void close()
    throws SQLException
    {
        delegate.close();
    }

    @Override
    public boolean execute(String sql)
    throws SQLException
    {
        return delegate.execute(sql);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys)
    throws SQLException
    {
        return delegate.execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes)
    throws SQLException
    {
        return delegate.execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames)
    throws SQLException
    {
        return delegate.execute(sql, columnNames);
    }

    @Override
    public int[] executeBatch()
    throws SQLException
    {
        return delegate.executeBatch();
    }

    @Override
    public ResultSet executeQuery(String sql)
    throws SQLException
    {
        return delegate.executeQuery(sql);
    }

    @Override
    public int executeUpdate(String sql)
    throws SQLException
    {
        return delegate.executeUpdate(sql);
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys)
    throws SQLException
    {
        return delegate.executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes)
    throws SQLException
    {
        return delegate.executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames)
    throws SQLException
    {
        return delegate.executeUpdate(sql, columnNames);
    }

    @Override
    public Connection getConnection()
    throws SQLException
    {
        return delegate.getConnection();
    }

    @Override
    public int getFetchDirection()
    throws SQLException
    {
        return delegate.getFetchDirection();
    }

    @Override
    public int getFetchSize()
    throws SQLException
    {
        return delegate.getFetchSize();
    }

    @Override
    public ResultSet getGeneratedKeys()
    throws SQLException
    {
        return delegate.getGeneratedKeys();
    }

    @Override
    public int getMaxFieldSize()
    throws SQLException
    {
        return delegate.getMaxFieldSize();
    }

    @Override
    public int getMaxRows()
    throws SQLException
    {
        return delegate.getMaxRows();
    }

    @Override
    public boolean getMoreResults()
    throws SQLException
    {
        return delegate.getMoreResults();
    }

    @Override
    public boolean getMoreResults(int current)
    throws SQLException
    {
        return delegate.getMoreResults(current);
    }

    @Override
    public int getQueryTimeout()
    throws SQLException
    {
        return delegate.getQueryTimeout();
    }

    @Override
    public ResultSet getResultSet()
    throws SQLException
    {
        return delegate.getResultSet();
    }

    @Override
    public int getResultSetConcurrency()
    throws SQLException
    {
        return delegate.getResultSetConcurrency();
    }

    @Override
    public int getResultSetHoldability()
    throws SQLException
    {
        return delegate.getResultSetHoldability();
    }

    @Override
    public int getResultSetType()
    throws SQLException
    {
        return delegate.getResultSetType();
    }

    @Override
    public int getUpdateCount()
    throws SQLException
    {
        return delegate.getUpdateCount();
    }

    @Override
    public SQLWarning getWarnings()
    throws SQLException
    {
        return delegate.getWarnings();
    }

    @Override
    public boolean isClosed()
    throws SQLException
    {
        return delegate.isClosed();
    }

    @Override
    public boolean isPoolable()
    throws SQLException
    {
        return delegate.isPoolable();
    }

    @Override
    public void setCursorName(String name)
    throws SQLException
    {
        delegate.setCursorName(name);
    }

    @Override
    public void setEscapeProcessing(boolean enable)
    throws SQLException
    {
        delegate.setEscapeProcessing(enable);
    }

    @Override
    public void setFetchDirection(int direction)
    throws SQLException
    {
        delegate.setFetchDirection(direction);
    }

    @Override
    public void setFetchSize(int rows)
    throws SQLException
    {
        delegate.setFetchSize(rows);
    }

    @Override
    public void setMaxFieldSize(int max)
    throws SQLException
    {
        delegate.setMaxFieldSize(max);
    }

    @Override
    public void setMaxRows(int max)
    throws SQLException
    {
        delegate.setMaxRows(max);
    }

    @Override
    public void setPoolable(boolean poolable)
    throws SQLException
    {
        delegate.setPoolable(poolable);
    }

    @Override
    public void setQueryTimeout(int seconds)
    throws SQLException
    {
        delegate.setQueryTimeout(seconds);
    }

    @Override
    public boolean isCloseOnCompletion()
    throws SQLException
    {
        return delegate.isCloseOnCompletion();
    }

    @Override
    public void closeOnCompletion()
    throws SQLException
    {
        delegate.closeOnCompletion();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface)
    {
        return iface.isInstance(delegate);
    }

    @Override
    public <T> T unwrap(Class<T> iface)
    {
        return iface.cast(delegate);
    }
}