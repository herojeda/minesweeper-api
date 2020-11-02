package org.hojeda.minesweeper.core.entity.board;

import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Board {

    private Long id;
    private Integer rowSize;
    private Integer columnSize;
    private Integer bombs;
    private BoardStatus status;
    private LocalDate createdAt;
    private Set<BoardField> fields;

    private Board(Builder builder) {
        setId(builder.id);
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

    public static Builder newBuilder(Board copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.rowSize = copy.getRowSize();
        builder.columnSize = copy.getColumnSize();
        builder.bombs = copy.getBombs();
        builder.status = copy.getStatus();
        builder.createdAt = copy.getCreatedAt();
        builder.fields = copy.getFields();
        return builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<BoardField> getFields() {
        return fields;
    }

    public void setFields(Set<BoardField> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return id.equals(board.id) &&
            rowSize.equals(board.rowSize) &&
            columnSize.equals(board.columnSize) &&
            bombs.equals(board.bombs) &&
            status == board.status &&
            createdAt.equals(board.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Board{" +
            "id=" + id +
            ", rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", bombs=" + bombs +
            ", status=" + status +
            ", createdAt=" + createdAt +
            ", fields=" + fields +
            '}';
    }


    public static final class Builder {
        private Long id;
        private Integer rowSize;
        private Integer columnSize;
        private Integer bombs;
        private BoardStatus status;
        private LocalDate createdAt;
        private Set<BoardField> fields;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
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

        public Builder withFields(Set<BoardField> val) {
            fields = val;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
