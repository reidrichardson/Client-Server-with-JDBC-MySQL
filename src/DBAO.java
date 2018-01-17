/*
    Name: Reid Richardson
    Course: CNT 4714 Fall 2017
    Assignment Title: Project 3 - Two-Tier Client-Server Application Development With MySQL and JDBC
    Date: October 15, 2017
 */

import java.sql.*;
import java.util.Vector;


public class DBAO {
    private Connection connector;
    private ResultSetMetaData metaData;
    private Vector<String> columns;
    /*DBAO constructor
      sets value of the connector to the passed Connection param
      param Connection connect is passed value
     */
    public DBAO(Connection connect) {

        this.connector = connect;
    }
    /* Executes the query and returns the vector
       Passes theQuery which is the user query to be executed
     */
    public Vector<Vector<String>> runQuery (String theQuery) throws SQLException{
        Vector<Vector<String>> results = new Vector<Vector<String>>();
        Statement st = this.connector.createStatement();
        ResultSet rs = st.executeQuery(theQuery);
        metaData = rs.getMetaData();

        int numcol = metaData.getColumnCount();
        setColumns(numcol, metaData);

        while(rs.next()) {
            Vector<String> row = new Vector<String>();
            for(int i = 1; i <= numcol; i++) {
                row.add(rs.getString(i));
            }
            results.add(row);
        }
        return results;
    }
    /*Returns a vector containing the columns from the executed query
      no parameters
     */
    public Vector<String> getColumns() throws SQLException {
        return this.columns;
    }

    /*sets the columns by populating them with metadata
      params: numcol, metadata

     */
    public void setColumns(int numcol, ResultSetMetaData metadata) throws SQLException {
        columns = new Vector<String>();
        for(int i = 1; i <= numcol; i++) {
            columns.add(metadata.getColumnName(i));
        }
    }

    /* runs an insert or delete query
       param theQuery
     */
    public int update(String theQuery) throws SQLException {
        Statement st = this.connector.createStatement();
        return st.executeUpdate(theQuery);
    }
}
