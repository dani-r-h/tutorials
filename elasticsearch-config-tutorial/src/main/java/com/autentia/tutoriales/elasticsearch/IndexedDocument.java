package com.autentia.tutoriales.elasticsearch;

import org.springframework.data.annotation.*;
import org.springframework.data.elasticsearch.annotations.*;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Document(indexName = "indice_prueba", type = "documento_indexado_type")
public class IndexedDocument {

	@Id
	private final String id;

	private final String title;

	private final String description;

	private final String notStored;

	@JsonCreator(mode = Mode.PROPERTIES)
	public IndexedDocument(@JsonProperty("id")
	final String id, @JsonProperty("title")
	final String title, @JsonProperty("description")
	final String description, @JsonProperty("notStored")
	final String notStored) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.notStored = notStored;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getNotStored() {
		return notStored;
	}

}
