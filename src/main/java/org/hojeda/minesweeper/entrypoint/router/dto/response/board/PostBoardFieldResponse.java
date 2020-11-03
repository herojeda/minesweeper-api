package org.hojeda.minesweeper.entrypoint.router.dto.response.board;

import java.util.Objects;

public class PostBoardFieldResponse {

    private Long id;
    private Integer row;
    private Integer column;
    private Integer value;
    private String status;

    public PostBoardFieldResponse() {
    }

    private PostBoardFieldResponse(Builder builder) {
        setId(builder.id);
        setRow(builder.row);
        setColumn(builder.column);
        setValue(builder.value);
        setStatus(builder.status);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(PostBoardFieldResponse copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.row = copy.getRow();
        builder.column = copy.getColumn();
        builder.value = copy.getValue();
        builder.status = copy.getStatus();
        return builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostBoardFieldResponse)) return false;
        PostBoardFieldResponse that = (PostBoardFieldResponse) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(row, that.row) &&
            Objects.equals(column, that.column) &&
            Objects.equals(value, that.value) &&
            Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PostBoardFieldResponse{" +
            "id=" + id +
            ", row=" + row +
            ", column=" + column +
            ", value=" + value +
            ", status='" + status + '\'' +
            '}';
    }


    public static final class Builder {
        private Long id;
        private Integer row;
        private Integer column;
        private Integer value;
        private String status;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withRow(Integer val) {
            row = val;
            return this;
        }

        public Builder withColumn(Integer val) {
            column = val;
            return this;
        }

        public Builder withValue(Integer val) {
            value = val;
            return this;
        }

        public Builder withStatus(String val) {
            status = val;
            return this;
        }

        public PostBoardFieldResponse build() {
            return new PostBoardFieldResponse(this);
        }
    }
}
