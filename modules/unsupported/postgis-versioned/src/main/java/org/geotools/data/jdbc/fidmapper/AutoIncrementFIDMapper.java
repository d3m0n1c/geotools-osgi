/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
 *    
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
/*
 * Created on 18-apr-2004
 * 26-may-2005 D. Adler Make subclass of AbstractFIDFilter.
 * 12-jul-2006 D. Adler GEOT-728 Refactor FIDMapper classes
 */
package org.geotools.data.jdbc.fidmapper;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Types;

import org.opengis.feature.simple.SimpleFeature;


/**
 * Support primary key columns that are automatically generated by the
 * database.
 *
 * @author wolf
 *
 * @source $URL: http://svn.osgeo.org/geotools/tags/8.0-M1/modules/unsupported/postgis-versioned/src/main/java/org/geotools/data/jdbc/fidmapper/AutoIncrementFIDMapper.java $
 * @deprecated scheduled for removal in 2.7, use classes in org.geotools.jdbc
 */
public class AutoIncrementFIDMapper extends AbstractFIDMapper {
    private static final long serialVersionUID = 1L;


    /**
     * Construct an AutoIncrementFIDMapper
     *
     * @param colName
     * @param dataType
     */
    public AutoIncrementFIDMapper(String colName, int dataType) {
    	this(null, null, colName, dataType);
    }

    /**
     * Construct an AutoIncrementFIDMapper
     *
     * @param tableSchemaName
     * @param tableName
     * @param colName
     * @param dataType
     */
    public AutoIncrementFIDMapper(String tableSchemaName, String tableName, String colName, int dataType) {
    	super(tableSchemaName, tableName);
        setInfo(colName, dataType, 0, 0, true);
    }
    /**
     * Construct an AutoIncrementFIDMapper
     *
     * @param tableName
     * @param colName
     * @param dataType
     */
    public AutoIncrementFIDMapper(String tableName, String colName, int dataType) {
    	this(null, tableName, colName, dataType);
    }
    /**
     * @see org.geotools.data.jdbc.fidmapper.FIDMapper#getID(java.lang.Object[])
     */
    public String getID(Object[] attributes) {
        if ((attributes != null) && (attributes.length == 1)
                && (attributes[0] != null)) {
            return attributes[0].toString();
        } else {
            return null;
        }
    }

    /**
     * @see org.geotools.data.jdbc.fidmapper.FIDMapper#getPKAttributes(java.lang.String)
     */
    public Object[] getPKAttributes(String FID) throws IOException {
        Object pk = null;

        switch (getColumnType(0)) {
        case Types.INTEGER:
        case Types.SMALLINT:
        case Types.TINYINT:
        	try {
        		pk = new Integer(Integer.parseInt(FID));
	        } catch(NumberFormatException nfe) {
	    		//if we get a really bad featureid we want to return something
	    	    //that will not mess up the database and throw an exception,
	    	    //we just want to not match against it, so we return -1
	    	    pk = new Integer(-1);
	    	}
            break;
        case Types.BIGINT:
            try {
                pk = new BigInteger(FID);
            } catch(NumberFormatException nfe) {
                //if we get a really bad featureid we want to return something
                //that will not mess up the database and throw an exception,
                //we just want to not match against it, so we return -1
                pk = new BigInteger("-1");
            }
            break;
        case Types.NUMERIC:
        	try {
        		pk = new Long(Long.parseLong(FID));
        	} catch(NumberFormatException nfe) {
        		//if we get a really bad featureid we want to return something
        	    //that will not mess up the database and throw an exception,
        	    //we just want to not match against it, so we return -1
        	    pk = new Integer(-1);
        	}
            break;
        case Types.VARCHAR: //unlikely to be used, but you never know...
        case Types.LONGVARCHAR:
        case Types.CHAR:
            pk = new String(FID);
            break;
        }

        return new Object[] { pk };
    }

    /**
     * @see org.geotools.data.jdbc.fidmapper.FIDMapper#createID(java.sql.Connection,
     *      org.geotools.feature.Feature, Statement)
     */
    public String createID(Connection conn, SimpleFeature feature, Statement statement)
        throws IOException {
        return null;
    }
    
    /**
     * @return {@code true} if fid is an integer, {@code false} othwerwise
     * @see FIDMapper#isValid(String)
     */
    public boolean isValid(String fid){
        Object pk;
        try {
            Object []pkAtts = getPKAttributes(fid);
            pk = pkAtts[0];
        } catch (IOException e) {
            return false;
        }
        if(pk instanceof Number){
            //getPKAttributes returns -1 whenever an invalid numeric fid is passed
            return ((Number)pk).longValue() != -1;
        }
        return pk instanceof String;
    }
    
}
