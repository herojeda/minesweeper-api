package org.hojeda.minesweeper.core.entity.board;

import org.hojeda.minesweeper.core.entity.board.field.BoardField;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Board {

    private Long id;
    private UUID uuid;
    private Integer rowSize;
    private Integer columnSize;
    private Integer mines;
    private BoardStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Set<BoardField> fields;

    private Board(Builder builder) {
        setId(builder.id);
        setUuid(builder.uuid);
        setRowSize(builder.rowSize);
        setColumnSize(builder.columnSize);
        setMines(builder.mines);
        setStatus(builder.status);
        setCreatedAt(builder.createdAt);
        setStartedAt(builder.startedAt);
        setFinishedAt(builder.finishedAt);
        setFields(builder.fields);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Board copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.uuid = copy.getUuid();
        builder.rowSize = copy.getRowSize();
        builder.columnSize = copy.getColumnSize();
        builder.mines = copy.getMines();
        builder.status = copy.getStatus();
        builder.createdAt = copy.getCreatedAt();
        builder.startedAt = copy.getStartedAt();
        builder.finishedAt = copy.getFinishedAt();
        builder.fields = copy.getFields();
        return builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return id.equals(board.id) &&
            uuid.equals(board.uuid) &&
            rowSize.equals(board.rowSize) &&
            columnSize.equals(board.columnSize) &&
            mines.equals(board.mines) &&
            status == board.status &&
            createdAt.equals(board.createdAt) &&
            Objects.equals(fields, board.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Board{" +
            "id=" + id +
            ", uuid=" + uuid +
            ", rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", miness=" + mines +
            ", status=" + status +
            ", createdAt=" + createdAt +
            ", fields=" + fields +
            '}';
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Integer getMines() {
        return mines;
    }

    public void setMines(Integer mines) {
        this.mines = mines;
    }

    public BoardStatus getStatus() {
        return status;
    }

    public void setStatus(BoardStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<BoardField> getFields() {
        return fields;
    }

    public void setFields(Set<BoardField> fields) {
        this.fields = fields;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public static final class Builder {
        private Long id;
        private UUID uuid;
        private Integer rowSize;
        private Integer columnSize;
        private Integer mines;
        private BoardStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime startedAt;
        private LocalDateTime finishedAt;
        private Set<BoardField> fields;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withUuid(UUID val) {
            uuid = val;
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

        public Builder withMines(Integer val) {
            mines = val;
            return this;
        }

        public Builder withStatus(BoardStatus val) {
            status = val;
            return this;
        }

        public Builder withCreatedAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder withStartedAt(LocalDateTime val) {
            startedAt = val;
            return this;
        }

        public Builder withFinishedAt(LocalDateTime val) {
            finishedAt = val;
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
