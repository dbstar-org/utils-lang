package io.github.dbstarll.utils.lang.test;

import io.github.dbstarll.utils.lang.line.BatchLineOperator;
import io.github.dbstarll.utils.lang.line.LineOperator;
import org.apache.commons.lang3.StringUtils;

public class LineTypeOperator implements LineOperator<LineType>, BatchLineOperator<LineType> {
    @Override
    public LineType[] operate(String... lines) {
        LineType[] rs = new LineType[lines.length];
        for (int i = 0; i < rs.length; i++) {
            rs[i] = operate(lines[i]);
        }
        return rs;
    }

    @Override
    public LineType operate(String line) {
        if (StringUtils.isBlank(line)) {
            return LineType.BLANK;
        } else if (line.startsWith("#")) {
            return LineType.COMMENT;
        } else if (line.startsWith("exception")) {
            throw new IllegalArgumentException();
        } else {
            return LineType.LINE;
        }
    }
}
