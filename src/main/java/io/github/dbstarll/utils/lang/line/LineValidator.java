package io.github.dbstarll.utils.lang.line;

import org.apache.commons.lang3.StringUtils;

/**
 * 行校验器接口.
 *
 * @author dbstar
 */
public interface LineValidator {
    /**
     * 检查行是否有效.
     *
     * @param line 待检查的行
     * @return 如果行有效，返回true，否则返回false
     */
    boolean isValidLine(String line);

    LineValidator ALL = new LineValidator() {
        @Override
        public boolean isValidLine(final String line) {
            return true;
        }
    };

    LineValidator NOT_BLANK = new LineValidator() {
        @Override
        public boolean isValidLine(final String line) {
            return StringUtils.isNotBlank(line);
        }
    };

    LineValidator NOT_COMMENT = new LineValidator() {
        @Override
        public boolean isValidLine(final String line) {
            return StringUtils.isNotBlank(line) && !line.startsWith("#");
        }
    };
}
