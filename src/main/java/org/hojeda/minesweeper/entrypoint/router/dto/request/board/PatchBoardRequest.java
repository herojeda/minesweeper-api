package org.hojeda.minesweeper.entrypoint.router.dto.request.board;

import java.util.Objects;

public class PatchBoardRequest {

    private Integer row;
    private Integer column;
    private String movementType;

    public PatchBoardRequest() {
    }

    private PatchBoardRequest(Builder builder) {
        setRow(builder.row);
        setColumn(builder.column);
        setMovementType(builder.movementType);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(PatchBoardRequest copy) {
        Builder builder = new Builder();
        builder.row = copy.getRow();
        builder.column = copy.getColumn();
        builder.movementType = copy.getMovementType();
        return builder;
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

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatchBoardRequest)) return false;
        PatchBoardRequest that = (PatchBoardRequest) o;
        return Objects.equals(row, that.row) &&
            Objects.equals(column, that.column) &&
            Objects.equals(movementType, that.movementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, movementType);
    }

    @Override
    public String toString() {
        return "PatchBoardRequest{" +
            "row=" + row +
            ", column=" + column +
            ", movementType='" + movementType + '\'' +
            '}';
    }

    public static final class Builder {
        private Integer row;
        private Integer column;
        private String movementType;

        private Builder() {
        }

        public Builder withRow(Integer val) {
            row = val;
            return this;
        }

        public Builder withColumn(Integer val) {
            column = val;
            return this;
        }

        public Builder withMovementType(String val) {
            movementType = val;
            return this;
        }

        public PatchBoardRequest build() {
            return new PatchBoardRequest(this);
        }
    }
}
