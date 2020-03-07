package com.hadwinling.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor {
	Object extractor(ResultSet resultSet) throws SQLException;
}
