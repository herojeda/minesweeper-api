package org.hojeda.minesweeper.entrypoint.router.dto.response.board;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class BoardResponse {

    private Long id;
    private Long userId;
    private Integer rowSize;
    private Integer columnSize;
    private Integer mines;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Set<BoardFieldResponse> fields;

    public BoardResponse() {
    }

    private BoardResponse(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
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

    public static Builder newBuilder(BoardResponse copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.userId = copy.getUserId();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public Set<BoardFieldResponse> getFields() {
        return fields;
    }

    public void setFields(Set<BoardFieldResponse> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardResponse)) return false;
        BoardResponse that = (BoardResponse) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(rowSize, that.rowSize) &&
            Objects.equals(columnSize, that.columnSize) &&
            Objects.equals(mines, that.mines) &&
            Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class Builder {
        private Long id;
        private Long userId;
        private Integer rowSize;
        private Integer columnSize;
        private Integer mines;
        private String status;
        private LocalDateTime createdAt;
        private LocalDateTime startedAt;
        private LocalDateTime finishedAt;
        private Set<BoardFieldResponse> fields;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withUserId(Long val) {
            userId = val;
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

        public Builder withStatus(String val) {
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

        public Builder withFields(Set<BoardFieldResponse> val) {
            fields = val;
            return this;
        }

        public BoardResponse build() {
            return new BoardResponse(this);
        }
    }
}
