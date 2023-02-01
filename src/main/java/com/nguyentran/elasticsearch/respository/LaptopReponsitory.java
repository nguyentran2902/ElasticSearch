package com.nguyentran.elasticsearch.respository;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Repository;

import com.nguyentran.elasticsearch.entity.Laptop;

@Repository
public class LaptopReponsitory {

	private static final String LAPTOP_INDEX = "laptop";

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	public List<Laptop> getAll(Integer pageNum, Integer pageSize) {
		// limmit + skip
		Query searchQuery = new NativeSearchQueryBuilder()
				.withPageable(PageRequest.of((pageNum - 1) * pageSize, pageSize)).build();

		SearchHits<Laptop> productHits = elasticsearchOperations.search(searchQuery, Laptop.class,
				IndexCoordinates.of(LAPTOP_INDEX));

		// convert to list
		List<Laptop> laptops = new ArrayList<>();
		productHits.forEach(lap -> {
			laptops.add(lap.getContent());
		});
		return laptops;
	}

	// search multi field
	public List<Laptop> searchLaptop(Integer pageNum, Integer pageSize, String query) {
//		QueryBuilder queryBuilder = QueryBuilders
//				.matchQuery("type", type).fuzziness(Fuzziness.TWO);
		QueryBuilder queryBuilder = QueryBuilders
				// search multi field
				.multiMatchQuery(query, "category.name", "type", "name", "system")
				// tìm kiếm mờ
				.fuzziness(Fuzziness.ONE);

		Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder)
				.withPageable(PageRequest.of(pageNum, pageSize)).build();

		SearchHits<Laptop> productHits = elasticsearchOperations.search(searchQuery, Laptop.class,
				IndexCoordinates.of(LAPTOP_INDEX));

		List<Laptop> laptops = new ArrayList<>();
		productHits.forEach(lap -> {
			laptops.add(lap.getContent());
		});

		return laptops;
	}

	// filter
	public List<Laptop> filter(Integer pageNum, Integer pageSize, String[] type, Integer[] price, String[] category,
			String[] screenSize, String[] cpu, String[] ram, String[] rom) {
		
		// Tạo string Query (nếu ko filter nào chọn thì mặc định get all)
		// Kết hợp các filter nên sử dụng AND ~ must
		String stringQuery = "{\"bool\":{\"must\":[{\"match_all\":{}}";

		// Lọc theo các filter
		// Có thể filter 1 mục theo nhiều điều kiện (category= dell, lenovo,...)
		// Convert input là 1 Array string thành string để query
		if (type != null)
			stringQuery += ",{\"match\":{\"type\": \"" + MyString(type) + "\"}}";
		if (category != null)
			stringQuery += ",{\"match\":{\"category.name\": \"" + MyString(category) + "\" }}";
		if (cpu != null)
			stringQuery += ",{\"match\":{\"cpu.name\": \"" + MyString(cpu) + "\" }}";
		if (ram != null)
			stringQuery += ",{\"match\":{\"ram.memory\": \"" + MyString(ram) + "\" }}";
		if (rom != null)
			stringQuery += ",{\"match\":{\"rom.memory\": \"" + MyString(rom) + "\" }}";
		

//		Lọc giá theo các khoảng đã định trc
//		Có thể lọc theo nhiều khoảng giá nên sử dụng OR ~ should
//		price==1 ~ <10000000
//		price==2 ~ 10000000~20000000
//		price==3 ~ 20000000~30000000
//		price==4 ~ >30000000
		if (price != null && price.length >= 1)
			stringQuery += ",{\"bool\" : { \"should\":[" + priceStringQuery(price) + "]}}";
		//end query
		stringQuery += "]}}";
		// Gộp các filter lại
		System.out.println(stringQuery);
		Query totalStringQuery = new StringQuery(stringQuery)
				.setPageable(PageRequest.of((pageNum - 1) * pageSize, pageSize));

		SearchHits<Laptop> productHits = elasticsearchOperations.search(totalStringQuery, Laptop.class,
				IndexCoordinates.of(LAPTOP_INDEX));

		// convert to list
		List<Laptop> laptops = new ArrayList<>();
		productHits.forEach(lap -> {
			laptops.add(lap.getContent());
		});

		return laptops;
	}

	// get price filter string query
	private String priceStringQuery(Integer[] price) {
		
		String query = "";
		System.out.println(price.length);

		for (int i = 0; i < price.length; i++) {
			String lastString = i + 1 == price.length ? "" : ",";

			if (price[i] == 1)
				query += "{\"range\":{\"price\":{\"lt\":\"10000000\"}}}" + lastString;
			else if (price[i] == 2)
				query += "{\"range\":{\"price\":{\"gte\":\"10000000\",\"lt\":\"20000000\"}}}" + lastString;
			else if (price[i] == 3)
				query += "{\"range\":{\"price\":{\"gte\":\"20000000\",\"lt\":\"30000000\"}}}" + lastString;
			else if (price[i] == 4)
				query += "{\"range\":{\"price\":{\"gte\":\"30000000\"}}}" + lastString;
		}

		return query;

	}

	// convert Array Of Strings To String
	private String MyString(String[] arrayString) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			String lastString = i + 1 == arrayString.length ? "" : ",";
			sb.append(arrayString[i] + lastString);
		}
		return sb.toString();
	}

	// fetchSuggestions: Gợi ý khi gõ tìm kiếm
	public List<String> fetchSuggestions(String query) {
		QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "*" + query + "*");

		Query searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).withPageable(PageRequest.of(0, 5))
				.build();

		SearchHits<Laptop> searchSuggestions = elasticsearchOperations.search(searchQuery, Laptop.class,
				IndexCoordinates.of(LAPTOP_INDEX));

		List<String> suggestions = new ArrayList<String>();

		searchSuggestions.forEach(lap -> {
			suggestions.add(lap.getContent().getName());
		});

		return suggestions;
	}

}
