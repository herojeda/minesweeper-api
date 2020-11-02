package org.hojeda.minesweeper.core.entity.board;

import java.util.Objects;

public class BasicBoardData {

    private Integer rowSize;
    private Integer columnSize;
    private Integer bombs;

    private BasicBoardData(Builder builder) {
        setRowSize(builder.rowSize);
        setColumnSize(builder.columnSize);
        setBombs(builder.bombs);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BasicBoardData copy) {
        Builder builder = new Builder();
        builder.rowSize = copy.getRowSize();
        builder.columnSize = copy.getColumnSize();
        builder.bombs = copy.getBombs();
        return builder;
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public void setRowSize(Integer rowSize) {
        this.rowSize = rowSize;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public Integer getBombs() {
        return bombs;
    }

    public void setBombs(Integer bombs) {
        this.bombs = bombs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicBoardData)) return false;
        BasicBoardData that = (BasicBoardData) o;
        return rowSize.equals(that.rowSize) &&
            columnSize.equals(that.columnSize) &&
            bombs.equals(that.bombs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSize, columnSize, bombs);
    }

    @Override
    public String toString() {
        return "BasicBoardData{" +
            "rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", bombs=" + bombs +
            '}';
    }


    public static final class Builder {
        private Integer rowSize;
        private Integer columnSize;
        private Integer bombs;

        private Builder() {
        }

        public Builder withRowSize(Integer val) {
            rowSize = val;
            return this;
        }

        public Builder withColumnSize(Integer val) {
            columnSize = val;
            return this;
        }

        public Builder withBombs(Integer val) {
            bombs = val;
            return this;
        }

        public BasicBoardData build() {
            return new BasicBoardData(this);
        }
    }
}
