package com.shcompany.job.parser.threads;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shcompany.job.parser.dao.DefinitionDAO;
import com.shcompany.job.parser.definition.model.Definition;


@Component
@Scope("prototype")
public class DefinitionsLoader implements Runnable{
	private static final Logger LOG = LoggerFactory.getLogger(DefinitionsLoader.class);

	@Autowired
    private DefinitionDAO definitionDAO;	
	
	@Override
	public void run() {
		List<Definition> ret=new ArrayList<Definition>();		
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();		
		Resource resource=null;
		resource = resolver.getResource("classpath:definitions/sites.json");
		
		/*List<String> excluded=Arrays.asList("Makefile.am", "Makefile.common", "Makefile.in");
		if(resources!=null)
			for (Resource r : resources) {			
				InputStream is;
				String content=null;
				try {
					if(excluded.contains(FilenameUtils.getName(r.getURL().getFile()))) continue;
					
					is = r.getInputStream();
					content = IOUtils.toString(is, "latin1");  					
					String[] arr=content.trim().split("%");
					Arrays.stream(arr).forEach(s->{
							if(s!=null&&!s.trim().equals(""))ret.add(new Fortune(s.trim()));
					});					
					is.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
				
		}	*/
		if(resource!=null){
			ObjectMapper mapper = new ObjectMapper();
			InputStream is;
			try {
				is = resource.getInputStream();
				ret= mapper.readValue( is, new TypeReference<List<Definition>>(){});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		    
		}
		
		
		
		
		
		LOG.info("Number of entries: {}",ret.size());
		
		definitionDAO.save(ret);
	}

	public void setDefinitionDAO(DefinitionDAO fortuneDAO) {
		this.definitionDAO = fortuneDAO;
	}
}
