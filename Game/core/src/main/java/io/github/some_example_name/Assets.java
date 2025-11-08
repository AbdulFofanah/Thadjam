package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Gdx;

public class Assets {
    public Texture wallTexture, floorTexture, characterTexture, enemyTexture_1,
                   benefitTexture_1, hiddenTexture_1, schoolTexture_1,
                   oldSchoolMazeTexture_1, classroomTexture_1, graduationCapTexture,
                   graduationTexture;
    public Music music;

    public Assets() {
        wallTexture = new Texture("brick_brown_0.png");
        characterTexture = new Texture("Run__000.png");
        floorTexture = new Texture("floor_sand_rock_0.png");
        enemyTexture_1 = new Texture("pizza_new.png");
        benefitTexture_1 = new Texture("sausage.png");
        hiddenTexture_1 = new Texture("honeycomb_old.png");
        schoolTexture_1 = new Texture("school.png");
        oldSchoolMazeTexture_1 = new Texture("old_school_maze.png");
        classroomTexture_1 = new Texture("classroom.png");
        graduationCapTexture = new Texture("graduation_cap.png");
        graduationTexture = new Texture("victory_graduation_screen.png");
    }

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

    }
}
