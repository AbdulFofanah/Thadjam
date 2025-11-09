package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

/**
 * Contains all the assets that are used throughout the program
 */
public class Assets {
    public Texture wallTexture, floorTexture, characterTexture, idlecharacterTexture, enemyTexture_1,
                   benefitTexture_1, hiddenTexture_1, schoolTexture_1,
                   oldSchoolMazeTexture_1, classroomTexture_1, graduationCapTexture,
                   graduationTexture, failureTexture;
    public Music music;
    public Animation<TextureRegion> playerRunAnimation;
    public Animation<TextureRegion> playerIdleAnimation;

    /**
     * Initialises all the files to variables
     * Creates variables for the varying animations for the character
     */
    public Assets() {
        wallTexture = new Texture("bricks.png");
        characterTexture = new Texture("frame-1.png");
        idlecharacterTexture = new Texture("idle-frame-1.png");
        floorTexture = new Texture("woodenfloor.png");
        enemyTexture_1 = new Texture("Cloud.png");
        benefitTexture_1 = new Texture("coffee1.png");
        hiddenTexture_1 = new Texture("giant_spore.png");
        schoolTexture_1 = new Texture("school.png");
        oldSchoolMazeTexture_1 = new Texture("old_school_maze.png");
        classroomTexture_1 = new Texture("classroom.png");
        graduationCapTexture = new Texture("graduation_cap.png");
        graduationTexture = new Texture("victory_graduation_screen.png");
        failureTexture =  new Texture("fail_screen.png");

        playerRunAnimation = loadRunAnimation();
        playerIdleAnimation = loadIdleAnimation();

    }

    /**
     * Creates an array that contains all the files for a running animation and initialises all of them to textures for use
     *
     * @return the animation loop
     *  */
    private Animation<TextureRegion> loadRunAnimation() {
        Array<TextureRegion> frames = new Array<>();

        for (int i = 1; i <= 5; i++) {
            String filename = "frame-" + i + ".png";
            Texture texture = new Texture(filename);
            frames.add(new TextureRegion(texture));
        }

        return new Animation<TextureRegion>(0.1f, frames, Animation.PlayMode.LOOP);
    }

    /**
     * Creates an array that contains all the files for an idling animation and initialises all of them to textures for use
     *
     * @return the animation loop
     *  */
    private Animation<TextureRegion> loadIdleAnimation(){
        Array<TextureRegion> frames = new Array<>();

        for (int i = 1; i <= 2; i++){
            String filename = "idle-frame-" + i +".png";
            Texture texture = new Texture(filename);
            frames.add(new TextureRegion(texture));
        }

        return new Animation<TextureRegion>(0.1f, frames, Animation.PlayMode.LOOP);
    }

    /**
     * Removes all the textures from RAM
     * Loops over the frames for the animated textures and removes them
     *  */
    public void dispose() {
        wallTexture.dispose();
        characterTexture.dispose();
        floorTexture.dispose();
        enemyTexture_1.dispose();
        benefitTexture_1.dispose();
        hiddenTexture_1.dispose();
        schoolTexture_1.dispose();
        oldSchoolMazeTexture_1.dispose();
        classroomTexture_1.dispose();
        graduationCapTexture.dispose();
        graduationTexture.dispose();
        failureTexture.dispose();

        for (TextureRegion region : playerRunAnimation.getKeyFrames()) {
            region.getTexture().dispose();
        }

        for (TextureRegion region : playerIdleAnimation.getKeyFrames()) {
            region.getTexture().dispose();
        }

    }
}
