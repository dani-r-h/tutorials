package com.autentia.tutoriales.elasticsearch.test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

import org.elasticsearch.index.query.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.autentia.tutoriales.elasticsearch.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/elasticsearch-context.xml")
public class IndexedDocumentTest_IT {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Before
	public void setUp() {
		elasticsearchTemplate.deleteIndex(IndexedDocument.class);
	}

	@Test
	public void givenIndexedDocumentWithNoSourceWhenRetriveNotStoredFieldThenReturnedNull() {
		// given
		final IndexQuery indexQuery = new IndexQuery();
		indexQuery.setObject(new IndexedDocument("1", "Documento indexado",
				"Esto es una prueba de indexación de contenido", "Este contenido no se deberia guardar."));
		elasticsearchTemplate.index(indexQuery);
		elasticsearchTemplate.refresh(IndexedDocument.class, true);

		// when
		final QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery());
		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withFields("notStored")
				.build();
		final List<IndexedDocument> indexedDocuments = elasticsearchTemplate.queryForList(searchQuery,
				IndexedDocument.class);

		// then
		assertThat(indexedDocuments.size(), is(equalTo(1)));
		assertThat(indexedDocuments.get(0).getNotStored(), is(nullValue()));
	}

	@Test
	public void givenIndexedDocumentWithNoSourceWhenRetrieveStoredTitleAndStoredDescriptionThenReturnTheTitleAndDescription() {
		// given
		final IndexQuery indexQuery = new IndexQuery();
		indexQuery.setObject(new IndexedDocument("1", "Documento indexado",
				"Esto es una prueba de indexación de contenido", "Este contenido no se deberia guardar."));
		elasticsearchTemplate.index(indexQuery);
		elasticsearchTemplate.refresh(IndexedDocument.class, true);

		// when
		final QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchAllQuery());
		final SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder)
				.withFields("title", "description").build();
		final List<IndexedDocument> indexedDocuments = elasticsearchTemplate.queryForList(searchQuery,
				IndexedDocument.class);

		// then
		assertThat(indexedDocuments.size(), is(equalTo(1)));
		assertThat(indexedDocuments.get(0).getTitle(), is(equalTo("Documento indexado")));
		assertThat(indexedDocuments.get(0).getDescription(),
				is(equalTo("Esto es una prueba de indexación de contenido")));
	}

}
