package org.elasticsearch.bootstrap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

//IMPORTANT: This class is needed since the -Dtests.jarhell.check has been disabled in Elasticsearch 5.0
//DO NOT REMOVE
public class JarHell {
	
	public static void checkJarHell() throws IOException, URISyntaxException {
		
		//IMPORTANT: This class is needed since the -Dtests.jarhell.check has been disabled in Elasticsearch 5.0
		//DO NOT REMOVE
	}
	
	public static URL[] parseClassPath()  {
		return new URL[0];
	}
}
