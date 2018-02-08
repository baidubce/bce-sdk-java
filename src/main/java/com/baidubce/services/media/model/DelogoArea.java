package com.baidubce.services.media.model;

public class DelogoArea {
    private Integer x = null;

    private Integer y = null;

    private Integer width = null;

    private Integer height = null;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public DelogoArea withX(Integer x) {
        this.x = x;
        return this;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public DelogoArea withY(Integer y) {
        this.y = y;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public DelogoArea withWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public DelogoArea withHeight(Integer height) {
        this.height = height;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DelogoArea {\n");

        sb.append("    x: ").append(x).append("\n");
        sb.append("    y: ").append(y).append("\n");
        sb.append("    width: ").append(width).append("\n");
        sb.append("    height: ").append(height).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
