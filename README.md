Just a more detailed description of what i've done

Main.java file:
- Changed `Main` to `extend com.badlogic.gdx.Game` instead of implementing `ApplicationListener`.
- Created and now manages shared resources (`SpriteBatch`, `BitmapFont`) accessible to screens.
- Implemented `create()` to initialize shared resources and set the initial `GameScreen` using `setScreen(new GameScreen(this))`.
- Simplified `render()` to delegate rendering to the active screen via `super.render()`.
- Updated `dispose()` to correctly dispose of shared resources.

GameScreen.java file:
- Changed `GameScreen` to `implements com.badlogic.gdx.Screen`.
- Added required `Screen` lifecycle methods (`show`, `render(float delta)`, `resize`, `pause`, `resume`, `hide`, `dispose`).
- Modified constructor to accept the `Main game` instance.
- Removed local `SpriteBatch` creation and disposal; now uses the shared `SpriteBatch` from the `Main` instance (`game.SpriteDrawing`).
- Added `OrthographicCamera` and linked it to the `FitViewport`.
- Updated `render(float delta)` to include camera/viewport updates and use the shared `SpriteBatch`.
- Moved input polling logic from `Player` into a new `handleInput(float delta)` method within `GameScreen`.

Player.java file:
- Removed direct input handling (`handleInput` method deleted).
- Added `setMovement(float dx, float dy)` method for `GameScreen` to pass movement intentions.
- Added `update(float delta, ...)` method to process movement intentions and call `tryMove`.
- Made `tryMove` private and updated its signature to accept collision rectangles as parameters.
- Added `getSpeed()`, `setPosition()`, and `getBoundingRectangle()` helper methods.
- Removed initial position setting from the constructor.
