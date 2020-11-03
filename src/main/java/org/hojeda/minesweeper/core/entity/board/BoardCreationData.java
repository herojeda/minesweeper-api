package org.hojeda.minesweeper.core.entity.board;

import org.hojeda.minesweeper.core.entity.board.field.BoardFieldCreationData;
import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BoardCreationData {

    private UUID uuid;
    private Integer rowSize;
    private Integer columnSize;
    private Integer mines;
    private BoardStatus status;
    private LocalDateTime createdAt;
    private List<BoardFieldCreationData> fields;

    private BoardCreationData(Builder builder) {
        setUuid(builder.uuid);
        setRowSize(builder.rowSize);
        setColumnSize(builder.columnSize);
        setMines(builder.mines);
        setStatus(builder.status);
        setCreatedAt(builder.createdAt);
        setFields(builder.fields);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BoardCreationData copy) {
        Builder builder = new Builder();
        builder.uuid = copy.getUuid();
        builder.rowSize = copy.getRowSize();
        builder.columnSize = copy.getColumnSize();
        builder.mines = copy.getMines();
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

    public List<BoardFieldCreationData> getFields() {
        return fields;
    }

    public void setFields(List<BoardFieldCreationData> fields) {
        this.fields = fields;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardCreationData)) return false;
        BoardCreationData that = (BoardCreationData) o;
        return rowSize.equals(that.rowSize) &&
            columnSize.equals(that.columnSize) &&
            mines.equals(that.mines) &&
            status == that.status &&
            createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "BoardCreationData{" +
            "uuid=" + uuid +
            ", rowSize=" + rowSize +
            ", columnSize=" + columnSize +
            ", mines=" + mines +
            ", status=" + status +
            ", createdAt=" + createdAt +
            ", fields=" + fields +
            '}';
    }

    public static final class Builder {
        private UUID uuid;
        private Integer rowSize;
        private Integer columnSize;
        private Integer mines;
        private BoardStatus status;
        private LocalDateTime createdAt;
        private List<BoardFieldCreationData> fields;

        private Builder() {
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

        public Builder withFields(List<BoardFieldCreationData> val) {
            fields = val;
            return this;
        }

        public BoardCreationData build() {
            return new BoardCreationData(this);
        }
    }
}
