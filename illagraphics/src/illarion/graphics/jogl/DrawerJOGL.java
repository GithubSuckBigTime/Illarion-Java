/*
 * This file is part of the Illarion Graphics Engine.
 *
 * Copyright © 2011 - Illarion e.V.
 *
 * The Illarion Graphics Engine is free software: you can redistribute i and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * The Illarion Graphics Engine is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * the Illarion Graphics Interface. If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.graphics.jogl;

import illarion.graphics.Drawer;
import illarion.graphics.Graphics;
import illarion.graphics.SpriteColor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.glsl.fixedfunc.FixedFuncUtil;

/**
 * The JOGL implementation of the drawer interface that is used to draw some
 * primitive objects using the JOGL render engine.
 * 
 * @author Martin Karing
 * @version 2.00
 * @since 2.00
 */
public final class DrawerJOGL implements Drawer {
    /**
     * The size of the border that is added in case a rectangle with rounded
     * edges shall be drawn. This helper value stores the smaller triangle edge
     * value.
     */
    protected static final int ROUNDED_BORDER_WIDTH_1 =
        (ROUNDED_BORDER_WIDTH * 5) / 6;

    /**
     * The size of the border that is added in case a rectangle with rounded
     * edges shall be drawn. This helper value stores the larger triangle edge
     * value.
     */
    protected static final int ROUNDED_BORDER_WIDTH_2 =
        (ROUNDED_BORDER_WIDTH * 1) / 2;
    /**
     * The float buffer that is used to draw the element to the screen.
     */
    private final FloatBuffer buffer = ByteBuffer
        .allocateDirect((Float.SIZE / 8) * 32).order(ByteOrder.nativeOrder())
        .asFloatBuffer();

    /**
     * The float buffer that is used to draw the colors on the screen.
     */
    private final FloatBuffer colorBuffer = ByteBuffer
        .allocateDirect((Float.SIZE / 8) * 32).order(ByteOrder.nativeOrder())
        .asFloatBuffer();

    /**
     * Draw a simple dot at a specified location on the screen.
     * 
     * @param x x coordinate of the dot on the screen
     * @param y y coordinate of the dot on the screen
     * @param size size of the dot on the screen
     * @param color color of the dot on the screen
     */
    @Override
    public void drawDot(final int x, final int y, final float size,
        final SpriteColor color) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);

            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWDOT);
            gl2.glPointSize(size / 2);
            color.setActiveColor();

            buffer.clear();
            buffer.put(x).put(y);
            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glDrawArrays(GL.GL_POINTS, 0, 1);
        }
    }

    /**
     * Draw a line between two points with a specified width and color.
     * 
     * @param x1 the x coordinate of the first point on the screen
     * @param y1 the y coordinate of the first point on the screen
     * @param x2 the x coordinate of the second point on the screen
     * @param y2 the y coordinate of the second point on the screen
     * @param width the width of the line
     * @param color the color of the line
     */
    @Override
    public void drawLine(final int x1, final int y1, final int x2,
        final int y2, final float width, final SpriteColor color) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWLINE);
            gl2.glLineWidth(width);
            color.setActiveColor();

            buffer.clear();
            buffer.put(x1).put(y1);
            buffer.put(x2).put(y2);
            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glDrawArrays(GL.GL_LINES, 0, 2);
        }
    }

    /**
     * Draw a quadrangle that is filled with the color set with the parameter.
     * This function offers the possibility to create a irregular shaped
     * quadrangle since all 4 dots can be set separately.
     * 
     * @param x1 the x coordinate of the first corner of the quadrangle
     * @param y1 the y coordinate of the first corner of the quadrangle
     * @param x2 the x coordinate of the second corner of the quadrangle
     * @param y2 the y coordinate of the second corner of the quadrangle
     * @param x3 the x coordinate of the third corner of the quadrangle
     * @param y3 the y coordinate of the third corner of the quadrangle
     * @param x4 the x coordinate of the fourth corner of the quadrangle
     * @param y4 the y coordinate of the fourth corner of the quadrangle
     * @param color color the color the quadrangle is filled with
     * @see illarion.graphics.Drawer#drawQuadrangle(int, int, int, int, int,
     *      int, int, int, SpriteColor)
     */
    @Override
    public void drawQuadrangle(final int x1, final int y1, final int x2,
        final int y2, final int x3, final int y3, final int x4, final int y4,
        final SpriteColor color) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWPOLY);
            color.setActiveColor();

            buffer.clear();
            buffer.put(x1).put(y1);
            buffer.put(x2).put(y2);
            buffer.put(x3).put(y3);
            buffer.put(x4).put(y4);
            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glDrawArrays(GL.GL_TRIANGLE_STRIP, 0, 4);
        }
    }

    /**
     * Draw a quadrangle frame. This function offers the possibility to create a
     * irregular shaped quadrangle since all 4 dots can be set separately.
     * 
     * @param x1 the x coordinate of the first corner of the quadrangle
     * @param y1 the y coordinate of the first corner of the quadrangle
     * @param x2 the x coordinate of the second corner of the quadrangle
     * @param y2 the y coordinate of the second corner of the quadrangle
     * @param x3 the x coordinate of the third corner of the quadrangle
     * @param y3 the y coordinate of the third corner of the quadrangle
     * @param x4 the x coordinate of the fourth corner of the quadrangle
     * @param y4 the y coordinate of the fourth corner of the quadrangle
     * @param color color the color the quadrangle line is drawn with
     */
    @Override
    public void drawQuadrangleFrame(final int x1, final int y1, final int x2,
        final int y2, final int x3, final int y3, final int x4, final int y4,
        final SpriteColor color) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWPOLY);
            color.setActiveColor();

            buffer.clear();
            buffer.put(x1).put(y1);
            buffer.put(x2).put(y2);
            buffer.put(x3).put(y3);
            buffer.put(x4).put(y4);
            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glColorPointer(4, GL.GL_FLOAT, 0, colorBuffer);
            gl2.glDrawArrays(GL.GL_LINE_LOOP, 0, 4);
        }
    }

    /**
     * Draw a rectangle that is filled with the color that is set as parameter.
     * 
     * @param x1 x coordinate of the first corner of the rectangle
     * @param y1 y coordinate of the first corner of the rectangle
     * @param x2 x coordinate of the second corner of the rectangle
     * @param y2 y coordinate of the second corner of the rectangle
     * @param color the color the rectangle is filled with
     */
    @Override
    public void drawRectangle(final int x1, final int y1, final int x2,
        final int y2, final SpriteColor color) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWOTHER);
            color.setActiveColor();

            buffer.clear();
            buffer.put(x1).put(y1);
            buffer.put(x2).put(y1);
            buffer.put(x2).put(y2);
            buffer.put(x1).put(y2);
            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glDrawArrays(GL2.GL_QUADS, 0, 4);
        }
    }

    /**
     * Draw a rectangle with different colors on each edge.
     * 
     * @param x1 x coordinate of the first corner of the rectangle
     * @param y1 y coordinate of the first corner of the rectangle
     * @param x2 x coordinate of the second corner of the rectangle
     * @param y2 y coordinate of the second corner of the rectangle
     * @param topLeftColor the color on the top left node of the rectangle
     * @param topRightColor the color on the top right node of the rectangle
     * @param bottomLeftColor the color on the bottom left node of the rectangle
     * @param bottomRightColor the color on the bottom right node of the
     *            rectangle
     */
    @Override
    public void drawRectangle(final int x1, final int y1, final int x2,
        final int y2, final SpriteColor topLeftColor,
        final SpriteColor topRightColor, final SpriteColor bottomLeftColor,
        final SpriteColor bottomRightColor) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWOTHERCOLOR);

            buffer.clear();
            colorBuffer.clear();

            buffer.put(x1).put(y1);
            topLeftColor.storeRGBA(colorBuffer);

            buffer.put(x1).put(y2);
            bottomLeftColor.storeRGBA(colorBuffer);

            buffer.put(x2).put(y1);
            topRightColor.storeRGBA(colorBuffer);

            buffer.put(x2).put(y2);
            bottomRightColor.storeRGBA(colorBuffer);

            buffer.flip();
            colorBuffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glColorPointer(4, GL.GL_FLOAT, 0, colorBuffer);
            gl2.glDrawArrays(GL.GL_TRIANGLE_STRIP, 0, 4);
        }
    }

    /**
     * Draw a rectangle that is not filled and consists just of the border.
     * 
     * @param x1 x coordinate of the first corner of the rectangle
     * @param y1 y coordinate of the first corner of the rectangle
     * @param x2 x coordinate of the second corner of the rectangle
     * @param y2 y coordinate of the second corner of the rectangle
     * @param color the color of the rectangle border line
     */
    @Override
    public void drawRectangleFrame(final int x1, final int y1, final int x2,
        final int y2, final float width, final SpriteColor color) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWOTHER);
            color.setActiveColor();

            gl2.glLineWidth(width);

            buffer.clear();
            buffer.put(x1).put(y1);
            buffer.put(x1).put(y2);
            buffer.put(x2).put(y2);
            buffer.put(x2).put(y1);
            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glDrawArrays(GL.GL_LINE_LOOP, 0, 4);
        }
    }

    /**
     * Draw a rounded rectangle. The size that is set by the parameters is the
     * inner rectangle that is not influenced by the rounded corners. The border
     * for the rounded edges is added to the size of the rectangle. So the
     * resulting object is slightly bigger then the size set here.
     * 
     * @param x1 x coordinate of the first corner of the rectangle
     * @param y1 y coordinate of the first corner of the rectangle
     * @param x2 x coordinate of the second corner of the rectangle
     * @param y2 y coordinate of the second corner of the rectangle
     * @param color the color the rectangle is filled with
     * @see illarion.graphics.Drawer#drawRoundedRectangle(int, int, int, int,
     *      SpriteColor)
     */
    @Override
    public void drawRoundedRectangle(final int x1, final int y1, final int x2,
        final int y2, final SpriteColor color) {

        final int quality = Graphics.getInstance().getQuality();
        if (quality == Graphics.QUALITY_MIN) {
            drawRectangle(x1 - ROUNDED_BORDER_WIDTH,
                y1 - ROUNDED_BORDER_WIDTH, x2 + ROUNDED_BORDER_WIDTH, y2
                    + ROUNDED_BORDER_WIDTH, color);
            return;
        }

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWOTHER);
            color.setActiveColor();

            buffer.clear();
            buffer.put(x1 - ROUNDED_BORDER_WIDTH).put(y1);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH).put(y2);

            buffer.put(x1 - ROUNDED_BORDER_WIDTH_1).put(
                y1 - ROUNDED_BORDER_WIDTH_2);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH_1).put(
                y2 + ROUNDED_BORDER_WIDTH_2);

            buffer.put(x1 - ROUNDED_BORDER_WIDTH_2).put(
                y1 - ROUNDED_BORDER_WIDTH_1);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH_2).put(
                y2 + ROUNDED_BORDER_WIDTH_1);

            buffer.put(x1 - ROUNDED_BORDER_WIDTH_2).put(
                y1 - ROUNDED_BORDER_WIDTH);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH_2).put(
                y2 + ROUNDED_BORDER_WIDTH);

            buffer.put(x2).put(y1 - ROUNDED_BORDER_WIDTH);
            buffer.put(x2).put(y2 + ROUNDED_BORDER_WIDTH);

            buffer.put(x2 + ROUNDED_BORDER_WIDTH_2).put(
                y1 - ROUNDED_BORDER_WIDTH_1);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH_2).put(
                y2 + ROUNDED_BORDER_WIDTH_1);

            buffer.put(x2 + ROUNDED_BORDER_WIDTH_1).put(
                y1 - ROUNDED_BORDER_WIDTH_2);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH_1).put(
                y2 + ROUNDED_BORDER_WIDTH_2);

            buffer.put(x2 + ROUNDED_BORDER_WIDTH).put(y1);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH).put(y2);

            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glDrawArrays(GL.GL_TRIANGLE_STRIP, 0, 16);
        }
    }

    @Override
    public void drawRoundedRectangleFrame(final int x1, final int y1,
        final int x2, final int y2, final float width, final SpriteColor color) {
        final int quality = Graphics.getInstance().getQuality();
        if (quality == Graphics.QUALITY_MIN) {
            drawRectangleFrame(x1 - ROUNDED_BORDER_WIDTH, y1
                - ROUNDED_BORDER_WIDTH, x2 + ROUNDED_BORDER_WIDTH, y2
                + ROUNDED_BORDER_WIDTH, width, color);
            return;
        }

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWOTHER);
            color.setActiveColor();
            gl2.glLineWidth(width);

            buffer.clear();
            buffer.put(x1 - ROUNDED_BORDER_WIDTH).put(y1);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH).put(y2);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH_1).put(
                y2 + ROUNDED_BORDER_WIDTH_2);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH_2).put(
                y2 + ROUNDED_BORDER_WIDTH_1);
            buffer.put(x1).put(y2 + ROUNDED_BORDER_WIDTH);
            buffer.put(x2).put(y2 + ROUNDED_BORDER_WIDTH);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH_2).put(
                y2 + ROUNDED_BORDER_WIDTH_1);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH_1).put(
                y2 + ROUNDED_BORDER_WIDTH_2);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH).put(y2);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH).put(y1);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH_1).put(
                y1 - ROUNDED_BORDER_WIDTH_2);
            buffer.put(x2 + ROUNDED_BORDER_WIDTH_2).put(
                y1 - ROUNDED_BORDER_WIDTH_1);
            buffer.put(x2).put(y1 - ROUNDED_BORDER_WIDTH);
            buffer.put(x1).put(y1 - ROUNDED_BORDER_WIDTH);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH_2).put(
                y1 - ROUNDED_BORDER_WIDTH_1);
            buffer.put(x1 - ROUNDED_BORDER_WIDTH_1).put(
                y1 - ROUNDED_BORDER_WIDTH_2);

            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glDrawArrays(GL.GL_LINE_LOOP, 0, 16);
        }
    }

    /**
     * Draw a triangle that is filled with the color that is set as parameter.
     * 
     * @param x1 x coordinate of the first corner of the triangle
     * @param y1 y coordinate of the first corner of the triangle
     * @param x2 x coordinate of the second corner of the triangle
     * @param y2 y coordinate of the second corner of the triangle
     * @param x3 x coordinate of the third corner of the triangle
     * @param y3 y coordinate of the third corner of the triangle
     * @param color color the color the triangle is filled with
     * @see illarion.graphics.Drawer#drawTriangle(int, int, int, int, int, int,
     *      SpriteColor)
     */
    @Override
    public void drawTriangle(final int x1, final int y1, final int x2,
        final int y2, final int x3, final int y3, final SpriteColor color) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWOTHER);
            color.setActiveColor();

            buffer.clear();
            buffer.put(x1).put(y1);
            buffer.put(x2).put(y2);
            buffer.put(x3).put(y3);
            buffer.flip();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, buffer);
            gl2.glDrawArrays(GL.GL_TRIANGLES, 0, 3);
        }
    }

    /**
     * Draw a triangle stripe using a set of coordinates from the buffer.
     * 
     * @param coords the float buffer that contains the coordinates to draw
     * @param color the sprite color that is used to draw the triangle stripe
     */
    @Override
    public void drawTriangles(final FloatBuffer coords, final SpriteColor color) {

        final GL gl = GLU.getCurrentGL();

        if (gl.isGL2ES1() || gl.hasGLSL()) {
            final GL2ES1 gl2 = FixedFuncUtil.wrapFixedFuncEmul(gl);
            DriverSettingsJOGL.getInstance().enableMode(gl,
                DriverSettingsJOGL.Modes.DRAWOTHER);
            color.setActiveColor();

            gl2.glVertexPointer(2, GL.GL_FLOAT, 0, coords);
            gl2.glDrawArrays(GL.GL_TRIANGLE_STRIP, 0, coords.remaining() >> 1);
        }
    }
}
