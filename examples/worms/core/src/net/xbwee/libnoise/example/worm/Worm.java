package net.xbwee.libnoise.example.worm;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import net.xbwee.libnoise.module.Perlin;
import net.xbwee.libnoise.noise.Noise;
import net.xbwee.libnoise.noise.NoiseQuality;


public class Worm {

    // Default worm lateral speed.
    static final double WORM_LATERAL_SPEED = (2.0 / 8192.0);

    // Default length of a worm segment, in screen units.
    static final double WORM_SEGMENT_LENGTH = (1.0 / 64.0);

    // Default segment count for each worm.
    static final int WORM_SEGMENT_COUNT = 112;

    // Default worm speed.
    static final double WORM_SPEED = (3.0 / 2048.0);

    // Default worm thickness.
    static final double WORM_THICKNESS = (4.0 / 256.0);

    // Default "twistiness" of the worms.
    static final double WORM_TWISTINESS = (4.0 / 256.0);


    ImmediateModeRenderer20 gl20 = new ImmediateModeRenderer20(false, true, 1);


    public Worm() {
        // The coordinates of the input value for the head segment must not
        // start at an integer boundary (for example, (0, 0, 0)).  At integer
        // boundaries, the coherent-noise values are always zero (blame gradient
        // noise for that), which would cause the worm to unrealistically
        // straighten those boundaries.
        m_headNoisePos.x =    7.0f / 2048.0f;
        m_headNoisePos.y = 1163.0f / 2048.0f;
        m_headNoisePos.z =  409.0f / 2048.0f;

        // Set up us the Perlin-noise module.
        m_noise.SetSeed (0);
        m_noise.SetFrequency (1.0);
        m_noise.SetLacunarity (2.375);
        m_noise.SetOctaveCount (3);
        m_noise.SetPersistence (0.5);
        m_noise.SetNoiseQuality (NoiseQuality.QUALITY_STD);

        // Set the worm parameters with their default values.
        m_headScreenPos.x = 0.0f;
        m_headScreenPos.y = 0.0f;
        m_lateralSpeed = WormsManager.WORM_LATERAL_SPEED;
        m_segmentCount = WormsManager.WORM_SEGMENT_COUNT;
        m_segmentLength = WormsManager.WORM_SEGMENT_LENGTH;
        m_speed = WormsManager.WORM_SPEED;
        m_thickness = WormsManager.WORM_THICKNESS;
        m_twistiness = WormsManager.WORM_TWISTINESS;

    }

    // Returns the taper amount for a specified segment.  A taper value of 0.0
    // indicates full tapering, while a taper value of 1.0 indicates no
    // tapering.  Taper values are at a minimum at both ends of the worm and
    // are at a maximum at the middle of the worm.
    double GetTaperAmount(int segment) {
        double curSegment = (double) segment;
        double segmentCount = (double) m_segmentCount;
        double halfSegmentCount = segmentCount / 2.0;
        double baseTaperAmount
                = 1.0 - Math.abs((curSegment / halfSegmentCount) - 1.0);
        return Math.sqrt(baseTaperAmount); // sqrt better defines the tapering.
    }

    // Sets the position of the worm's head segment in screen space.
    void SetHeadScreenPos(final Vector2 pos) {
        m_headScreenPos = pos;
    }

    // Sets the worm's lateral speed.  This is the amount the worm "thrashes
    // around" between frames.  Higher values increases the thrashing amount.
    void SetLateralSpeed(double lateralSpeed) {
        m_lateralSpeed = lateralSpeed;
    }

    // Sets the seed of the Perlin-noise module.
    void SetSeed(int seed) {
        //m_noise.SetSeed (seed);
    }

    // Sets the number of segments that make up the worm.
    void SetSegmentCount(int segmentCount) {
        m_segmentCount = segmentCount;
    }

    // Sets the length of a worm segment, in screen units.
    void SetSegmentLength(double segmentLength) {
        m_segmentLength = segmentLength;
    }

    // Sets the worm's speed.  Higher values increase the speed.
    void SetSpeed(double speed) {
        m_speed = speed;
    }

    // Sets the worm's thickness, in screen units.
    void SetThickness(double thickness) {
        m_thickness = thickness;
    }

    // Defines the "twistiness" of the worms.  Higher values produce more
    // contorted worms.
    void SetTwistiness(double twistiness) {
        m_twistiness = twistiness;
    }

    // Updates the worm's segment positions.  This must be called after each
    // frame.
    public void Update() {
        // The angle of the head segment is used to determine the direction the worm
        // moves.  The worm moves in the opposite direction.
        double noiseValue = m_noise.GetValue(
                m_headNoisePos.x,
                m_headNoisePos.y,
                m_headNoisePos.z);
        m_headScreenPos.x -= (Math.cos(noiseValue * 2.0 * Math.PI) * m_speed);
        m_headScreenPos.y -= (Math.sin(noiseValue * 2.0 * Math.PI) * m_speed);

        // Slightly update the coordinates of the input value, in "noise space".
        // This causes the worm's shape to be slightly different in the next frame.
        // The x coordinate of the input value is shifted in a negative direction,
        // which propagates the previous Perlin-noise values over to subsequent
        // segments.  This produces a "slithering" effect.
        m_headNoisePos.x -= m_speed * 2.0;
        m_headNoisePos.y += m_lateralSpeed;
        m_headNoisePos.z += m_lateralSpeed;

        // Make sure the worm's head is within the window, otherwise the worm may
        // escape.  Horrible, horrible freedom!
        m_headScreenPos.x = (float) Math.max(-1.0, Math.min(m_headScreenPos.x, 1.0));
        m_headScreenPos.y = (float) Math.max(-1.0, Math.min(m_headScreenPos.y, 1.0));
    }

    public void Draw(Camera camera) {
        // The worm is drawn using a triangle strip.
        gl20.begin(camera.combined, GL20.GL_TRIANGLE_STRIP);

        // Position of the current segment being drawn, in screen space.
        Vector2 curSegmentScreenPos = m_headScreenPos;

        // The width of the worm's body at the current segment being drawn.
        Vector2 offsetPos = new Vector2();

        // Coordinates of the input value, in "noise space", that specifies the
        // current segment's angle.
        Vector3 curNoisePos = new Vector3();

        // The vector that is perpindicular to the center of the segment; used to
        // determine the position of the edges of the worm's body.
        Vector2 curNormalPos = new Vector2();

        for (int curSegment = 0; curSegment < m_segmentCount; curSegment++) {

            // Get the Perlin-noise value for this segment based on the segment
            // number.  This value is interpreted as an angle, in radians.
            curNoisePos.x = m_headNoisePos.x + (curSegment * (float) m_twistiness);
            curNoisePos.y = m_headNoisePos.y;
            curNoisePos.z = m_headNoisePos.z;
            double noiseValue = m_noise.GetValue(
                    curNoisePos.x,
                    curNoisePos.y,
                    curNoisePos.z);

            // Determine the width of the worm's body at this segment.
            double taperAmount = GetTaperAmount(curSegment) * m_thickness;

            // Determine the offset of this segment from the previous segment by
            // converting the angle from the Perlin-noise module to an (x, y)
            // coordinate.
            offsetPos.x = (float) Math.cos(noiseValue * 2.0 * Math.PI);
            offsetPos.y = (float) Math.sin(noiseValue * 2.0 * Math.PI);

            // Determine the coordinates of each corner of the segment.
            curNormalPos.x = (-offsetPos.y) * (float) taperAmount;
            curNormalPos.y = (offsetPos.x) * (float) taperAmount;
            offsetPos.x *= m_segmentLength;
            offsetPos.y *= m_segmentLength;
            float x0 = curSegmentScreenPos.x + curNormalPos.x;
            float y0 = curSegmentScreenPos.y + curNormalPos.y;
            float x1 = curSegmentScreenPos.x - curNormalPos.x;
            float y1 = curSegmentScreenPos.y - curNormalPos.y;

            // Draw the segment using OpenGL.
            gl20.texCoord(curSegment, 0.0f);
            gl20.vertex(x0, y0, 0f);
            gl20.color(255, 0, 0, 255);
            gl20.texCoord(curSegment, 1.0f);
            gl20.vertex(x1, y1, 0f);
            gl20.color(0, 255, 0, 255);
            // Prepare the next segment.
            ++curSegment;
            curSegmentScreenPos.x += offsetPos.x;
            curSegmentScreenPos.y += offsetPos.y;
        }

        // Finish drawing the worm.
        gl20.end();
    }

    // Coordinates of the input value that generates the Perlin noise in
    // "noise space".  This is used to specify the angles of the worm's
    // segments.
    Vector3 m_headNoisePos = new Vector3();

    // Position of the worm's head segment, in screen space.
    Vector2 m_headScreenPos = new Vector2();

    // Worm's lateral speed.
    double m_lateralSpeed;

    // Noise module used to draw the worm.
    Perlin m_noise = new Perlin();

    // Number of segments that make up the worm.
    int m_segmentCount;

    // Length of a worm segment.
    double m_segmentLength;

    // Worm speed.
    double m_speed;

    // Worm thickness.
    double m_thickness;

    // "Twistiness" of the worm.
    double m_twistiness;

    // Default worm lateral speed.
    double g_wormLateralSpeed = WormsManager.WORM_LATERAL_SPEED;

    // Default length of a worm segment, in screen units.
    double g_wormSegmentLength = WormsManager.WORM_SEGMENT_LENGTH;

    // Default segment count for each worm.
    int g_wormSegmentCount = WormsManager.WORM_SEGMENT_COUNT;

    // Default worm speed.
    double g_wormSpeed = WormsManager.WORM_SPEED;

    // Default worm thickness.
    double g_wormThickness = WormsManager.WORM_THICKNESS;

    // Default "twistiness" of the worms.
    double g_wormTwistiness = WormsManager.WORM_TWISTINESS;

    // Number of worms rendered on the screen.
    int g_curWormCount = 32;
}