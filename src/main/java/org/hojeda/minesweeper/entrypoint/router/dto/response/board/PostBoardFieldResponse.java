package org.hojeda.minesweeper.entrypoint.router.dto.response.board;

import java.util.Objects;

public class PostBoardFieldResponse {

    private Long id;
    private Integer rowNumber;
    private Integer columnNumber;
    private Integer value;
    private Boolean hidden;

    public PostBoardFieldResponse() {
    }

    private PostBoardFieldResponse(Builder builder) {
        setId(builder.id);
        setRowNumber(builder.rowNumber);
        setColumnNumber(builder.columnNumber);
        setValue(builder.value);
        setHidden(builder.hidden);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(PostBoardFieldResponse copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.rowNumber = copy.getRowNumber();
        builder.columnNumber = copy.getColumnNumber();
        builder.value = copy.getValue();
        builder.hidden = copy.getHidden();
        return builder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostBoardFieldResponse)) return false;
        PostBoardFieldResponse that = (PostBoardFieldResponse) o;
        return id.equals(that.id) &&
            rowNumber.equals(that.rowNumber) &&
            columnNumber.equals(that.columnNumber) &&
            value.equals(that.value) &&
            hidden.equals(that.hidden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PostBoardFieldResponse{" +
            "id=" + id +
            ", rowNumber=" + rowNumber +
            ", columnNumber=" + columnNumber +
            ", value=" + value +
            ", hidden=" + hidden +
            '}';
    }


    public static final class Builder {
        private Long id;
        private Integer rowNumber;
        private Integer columnNumber;
        private Integer value;
        private Boolean hidden;

        private Builder() {
        }

        public Builder withId(Long val) {
            id = val;
            return this;
        }

        public Builder withRowNumber(Integer val) {
            rowNumber = val;
            return this;
        }

        public Builder withColumnNumber(Integer val) {
            columnNumber = val;
            return this;
        }

        public Builder withValue(Integer val) {
            value = val;
            return this;
        }

        public Builder withHidden(Boolean val) {
            hidden = val;
            return this;
        }

        public PostBoardFieldResponse build() {
            return new PostBoardFieldResponse(this);
        }
    }
}
