package org.geotools.data.ingres;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.jdbc.AutoGeneratedPrimaryKeyColumn;
import org.geotools.jdbc.JDBCFeatureStore;
import org.geotools.jdbc.JDBCPrimaryKeyTest;
import org.geotools.jdbc.JDBCPrimaryKeyTestSetup;
import org.geotools.jdbc.SequencedPrimaryKeyColumn;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

public class IngresPrimaryKeyTest extends JDBCPrimaryKeyTest {

	@Override
	protected JDBCPrimaryKeyTestSetup createTestSetup() {
		return new IngresPrimaryKeyTestSetup(new IngresTestSetup());
	}
	
    public void testAutoGeneratedPrimaryKey() throws Exception {
    	//Ingres 10 supports auto generated primary keys
    	//however the current JDBC driver does not
    	//implement when available
    }
        
}