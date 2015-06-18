package com.pentalog.us.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import com.pentalog.us.model.Product;

public class PropertyMapDeserializer extends JsonDeserializer<Map<Product,Integer>>  {

	@Override
	public Map<Product, Integer> deserialize(JsonParser jp,DeserializationContext arg1) throws IOException, JsonProcessingException {
		
		Map<Product, Integer> products = new HashMap<Product,Integer>();
		JsonNode node = jp.getCodec().readTree(jp);
		Iterator<JsonNode> elements =  node.getElements();
		
		while (elements.hasNext()){
			JsonNode temp = elements.next();
			Product product = new ObjectMapper().readValue(temp.get("product").toString(), Product.class);
			products.put(product, temp.get("quantity").getIntValue());
		}
		 
		return products;
	}
}