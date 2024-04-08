module Coding_Programming_FBLA {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	requires fontawesomefx;
	requires langchain4j.core;
	requires langchain4j.open.ai;

	opens application to javafx.graphics, javafx.fxml, javafx.base;
}