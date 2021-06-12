package com.mygdx.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameUI extends Stage {

    private final GameScreen screen;

    private OrthographicCamera uiCamera;

    private ExtendViewport uiViewport;

    private Skin skin;

    private Table table;

    private Pixmap bgPixmap;

    private TextureRegionDrawable tableBg;

    private Label menuTitleLabel;

    private Label tileTypeLabel;

    private Label genTypeLabel;

    private Label mazeWidthLabel;

    private Label mazeHeightLabel;

    private Label zoomLabel;

    private SelectBox<GenerationType> genTypeSelectBox;

    private TextField mazeWidthInput;

    private TextField mazeHeightInput;

    private TextField zoomInput;

    private TextButton generateButton;

    public GameUI(final GameScreen screen)
    {
        this.screen = screen;

        this.initUI();
        this.initUIEvents();
    }

    private void initUI()
    {

        //Create UI Camera and Viewport
        this.uiCamera = new OrthographicCamera();
        this.uiViewport = new ExtendViewport(Constants.LOOK_WIDTH, Constants.LOOK_HEIGHT, this.uiCamera);
        this.uiCamera.setToOrtho(false, Constants.LOOK_WIDTH, Constants.LOOK_HEIGHT);

        //Load UI Skin and Create Stage
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.setViewport(uiViewport);

        //Create the Primary Table
        this.table = new Table();
        this.table.setPosition(100f, 75f);
        this.table.setWidth(300);
        this.table.setHeight(Constants.LOOK_HEIGHT - 150f);
        this.table.align(Align.top);
        this.table.pad(10f);

        this.bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        this.bgPixmap.setColor(Color.GRAY);
        this.bgPixmap.fill();

        this.tableBg = new TextureRegionDrawable(new TextureRegion(new Texture(this.bgPixmap)));
        this.table.setBackground(this.tableBg);

        this.addActor(this.table);
        this.table.row().padBottom(10f);

        //Menu Title Label
        this.menuTitleLabel = new Label("Maze Generation Settings", this.skin);
        this.menuTitleLabel.setColor(Color.WHITE);
        this.table.add(this.menuTitleLabel).colspan(2).align(Align.center);
        this.table.row().padBottom(10f);

        //Tile Type Label and Select Box
        this.tileTypeLabel = new Label("Tile: ", this.skin);
        this.tileTypeLabel.setColor(Color.WHITE);
        this.table.add(this.tileTypeLabel).align(Align.left);

        //Generation Type Label and Select Box
        this.genTypeLabel = new Label("Gen: ", this.skin);
        this.genTypeLabel.setColor(Color.WHITE);
        this.table.add(this.genTypeLabel).align(Align.left);

        this.genTypeSelectBox = new SelectBox<GenerationType>(this.skin);
        this.genTypeSelectBox.setItems(GenerationType.values());
        this.table.add(this.genTypeSelectBox).align(Align.left).fillX();
        this.table.row().padBottom(10f);

        //Maze Width Label and Input
        this.mazeWidthLabel = new Label("Width: ", this.skin);
        this.mazeWidthLabel.setColor(Color.WHITE);
        this.table.add(this.mazeWidthLabel).align(Align.left);

        this.mazeWidthInput = new TextField(Integer.toString(screen.getMazeWidth()), this.skin);
        this.table.add(this.mazeWidthInput).align(Align.left).fillX();
        this.table.row().padBottom(10f);

        //Maze Height Label and Input
        this.mazeHeightLabel = new Label("Height: ", this.skin);
        this.mazeHeightLabel.setColor(Color.WHITE);
        this.table.add(this.mazeHeightLabel).align(Align.left);

        this.mazeHeightInput = new TextField(Integer.toString(screen.getMazeHeight()), this.skin);
        this.table.add(this.mazeHeightInput).align(Align.left).fillX();
        this.table.row().padBottom(10f);

        //Zoom Label and Input
        this.zoomLabel = new Label("Zoom: ", this.skin);
        this.zoomLabel.setColor(Color.WHITE);
        this.table.add(this.zoomLabel).align(Align.left);

        this.zoomInput = new TextField(Float.toString(MazeGame.camera.zoom), this.skin);
        this.table.add(this.zoomInput).align(Align.left).fillX();
        this.table.row().padBottom(10f);

        //Generate Button
        this.generateButton = new TextButton("Generate", this.skin);
        this.table.add(this.generateButton).colspan(2).align(Align.center).fillX();

        this.table.setVisible(false);
        this.uiCamera.zoom = 1.5f;
    }

    private void initUIEvents()
    {
        this.generateButton.addListener( new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                getAndSetMazeWidth();
                getAndSetMazeHeight();
                getAndSetCameraZoom();

                screen.createMaze(screen.getGenType(), screen.getMazeWidth(), screen.getMazeHeight());
            }
        });
    }

    private void getAndSetMazeWidth()
    {
        try
        {
            int width = Integer.parseInt(mazeWidthInput.getText());
            if(width > 1 && width <= 1000)
            {
                screen.setMazeWidth(width);
            }
            else
            {
                mazeWidthInput.setText(Integer.toString(screen.getMazeWidth()));
            }
        }
        catch(NumberFormatException e)
        {
            mazeWidthInput.setText(Integer.toString(screen.getMazeWidth()));
        }
    }

    private void getAndSetMazeHeight()
    {
        try
        {
            int height = Integer.parseInt(mazeHeightInput.getText());
            if(height > 1 && height <= 1000)
            {
                screen.setMazeHeight(height);
            }
            else
            {
                mazeHeightInput.setText(Integer.toString(screen.getMazeHeight()));
            }
        }
        catch(NumberFormatException e)
        {
            mazeHeightInput.setText(Integer.toString(screen.getMazeHeight()));
        }
    }

    private void getAndSetCameraZoom()
    {
        try
        {
            float zoom = Float.parseFloat(zoomInput.getText());
            if(zoom >= 1f)
            {
                MazeGame.camera.zoom = zoom;
            }
            else
            {
                zoomInput.setText(Float.toString(MazeGame.camera.zoom));
            }
        }
        catch(NumberFormatException e)
        {
            zoomInput.setText(Float.toString(MazeGame.camera.zoom));
        }
    }

    //Show or Hide Settings Menu
    @Override
    public void act(float delta)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.H))
        {
            if(!this.table.isVisible())
            {
                this.table.setVisible(true);
                Gdx.input.setInputProcessor(this);
                this.mazeWidthInput.setText(Integer.toString(screen.getMazeWidth()));
                this.mazeHeightInput.setText(Integer.toString(screen.getMazeHeight()));
                this.zoomInput.setText(Float.toString(MazeGame.camera.zoom));
            }
            else
            {
                this.table.setVisible(false);
            }
        }

        super.act(delta);
    }
}
