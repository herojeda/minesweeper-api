package org.hojeda.minesweeper.core.entity.board;

import org.hojeda.minesweeper.core.entity.board.field.BoardFieldCreationData;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BoardCreationData {

    private Integer rowSize;
    private Integer columnSize;
    private Integer bombs;
    private BoardStatus status;
    private LocalDate createdAt;
    private List<BoardFieldCreationData> fields;

    private BoardCreationData(Builder builder) {
        setRowSize(builder.rowSize);
        setColumnSize(builder.columnSize);
        setBombs(builder.bombs);
        setStatus(builder.status);
        setCreatedAt(builder.createdAt);
        setFields(builder.fields);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BoardCreationData copy) {
        Builder builder = new Builder();
        builder.rowSize = copy.getRowSize();
        builder.columnSize = copy.getColumnSize();
        builder.bombs = copy.getBombs();
        builder.status = copy.getStatus();
        builder.createdAt = copy.getCreatedAt();
        builder.fields = copy.getFields();
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

    public BoardStatus getStatus() {
        return status;
    }

    public void setStatus(BoardStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<BoardFieldCreationData> getFields() {
        return fields;
    }

    public void setFields(List<BoardFieldCreationData> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardCreationData)) return false;
        BoardCreationData that = (BoardCreationData) o;
        return rowSize.equals(that.rowSize) &&
            columnSize.equals(that.columnSize) &&
            bombs.equals(that.bombs) &&
            status == that.status &&
            createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowSize, columnSize, bombs, status, createdAt);
    }

    @Override
    public String toString() {
        return "BoardCreationData{" +
            "rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", bombs=" + bombs +
            ", status=" + status +
            ", createdAt=" + createdAt +
            ", fields=" + fields +
            '}';
    }


    public static final class Builder {
        private Integer rowSize;
        private Integer columnSize;
        private Integer bombs;
        private BoardStatus status;
        private LocalDate createdAt;
        private List<BoardFieldCreationData> fields;

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

        public Builder withStatus(BoardStatus val) {
            status = val;
            return this;
        }

        public Builder withCreatedAt(LocalDate val) {
            createdAt = val;
            return this;
        }

        public Builder withFields(List<BoardFieldCreationData> val) {
            fields = val;
            return this;
        }

        public BoardCreationData build() {
            return new BoardCreationData(this);
        }
    }
}
