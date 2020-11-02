package org.hojeda.minesweeper.entrypoint.router.dto.response.board;

import org.hojeda.minesweeper.core.entity.constants.board.BoardStatus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class PostBoardResponse {

    private Long id;
    private Integer rowSize;
    private Integer columnSize;
    private Integer bombs;
    private String status;
    private LocalDate createdAt;
    private Set<PostBoardFieldResponse> fields;

    private PostBoardResponse(Builder builder) {
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

    public static Builder newBuilder(PostBoardResponse copy) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Set<PostBoardFieldResponse> getFields() {
        return fields;
    }

    public void setFields(Set<PostBoardFieldResponse> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostBoardResponse)) return false;
        PostBoardResponse that = (PostBoardResponse) o;
        return id.equals(that.id) &&
            rowSize.equals(that.rowSize) &&
            columnSize.equals(that.columnSize) &&
            bombs.equals(that.bombs) &&
            status == that.status &&
            createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PostBoardResponse{" +
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
        private String status;
        private LocalDate createdAt;
        private Set<PostBoardFieldResponse> fields;

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

        public Builder withStatus(String val) {
            status = val;
            return this;
        }

        public Builder withCreatedAt(LocalDate val) {
            createdAt = val;
            return this;
        }

        public Builder withFields(Set<PostBoardFieldResponse> val) {
            fields = val;
            return this;
        }

        public PostBoardResponse build() {
            return new PostBoardResponse(this);
        }
    }
}
