## libGdx源码分析
#### 一个项目的启动
- 代码
```java
LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
config.height=200;
config.width=200;
new LwjglApplication(new MyGdxGame(), config);
```
- 详细介绍
	- LwjglApplicationConfiguration
      里面是一些默认设置，比如宽高，是否自动播放音频等
	- new LwjglApplication(new MyGdxGame(), config);
	  方法中设置ApplicationListener和config

```java
public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
}
```
- 创建一个batch
	- 创建一个顶点数组
	- 创建一个矩阵
		```java
		vertices = new float[size * Sprite.SPRITE_SIZE];
		static final int VERTEX_SIZE = 2 + 1 + 2;  //顶点的个数
		static final int SPRITE_SIZE = 4 * VERTEX_SIZE;//创建一下顶点的大小
		```
	```java 
	public SpriteBatch () {
		一个默认的缓冲区和一个默认的buffer
		this(1000, null);
	}
	public SpriteBatch (int size, ShaderProgram defaultShader) {
		// 32767 is max index, so 32767 / 6 - (32767 / 6 % 3) = 5460.
		if (size > 5460) throw new IllegalArgumentException("Can't have more than 5460 sprites per batch: " + size);

		Mesh.VertexDataType vertexDataType = Mesh.VertexDataType.VertexArray;
		if (Gdx.gl30 != null) {
			vertexDataType = Mesh.VertexDataType.VertexBufferObjectWithVAO;
		}
		mesh = new Mesh(vertexDataType, false, size * 4, size * 6, new VertexAttribute(Usage.Position, 2,
			ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(Usage.ColorPacked, 4, ShaderProgram.COLOR_ATTRIBUTE),
			new VertexAttribute(Usage.TextureCoordinates, 2, ShaderProgram.TEXCOORD_ATTRIBUTE + "0"));

		projectionMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		vertices = new float[size * Sprite.SPRITE_SIZE];

		int len = size * 6;
		short[] indices = new short[len];
		short j = 0;
		for (int i = 0; i < len; i += 6, j += 4) {
			indices[i] = j;
			indices[i + 1] = (short)(j + 1);
			indices[i + 2] = (short)(j + 2);
			indices[i + 3] = (short)(j + 2);
			indices[i + 4] = (short)(j + 3);
			indices[i + 5] = j;
		}
		mesh.setIndices(indices);

		if (defaultShader == null) {
			shader = createDefaultShader();
			ownsShader = true;
		} else
			shader = defaultShader;
	}

	```
	他需要一个缓存区大小，和一个默认的着色器。
	```java
	片源着色器
	//POSITION_ATTRIBUTE = "a_position";
	String vertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			//COLOR_ATTRIBUTE = "a_color";
			+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			TEXCOORD_ATTRIBUTE = "a_texCoord";
			+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			//一个矩阵
			+ "uniform mat4 u_projTrans;\n" //
			//传入到片段着色器
			+ "varying vec4 v_color;\n" //
			+ "varying vec2 v_texCoords;\n" //
			+ "\n" //
			+ "void main()\n" //
			+ "{\n" //
			//颜色传递给片段
			+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			//将a传递给片段（透明度）
			+ "   v_color.a = v_color.a * (255.0/254.0);\n" //
			//
			+ "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			//将每个点与矩阵变换，
			+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "}\n";
		String fragmentShader = "#ifdef GL_ES\n" //
			+ "#define LOWP lowp\n" //
			+ "precision mediump float;\n" //
			+ "#else\n" //
			+ "#define LOWP \n" //
			+ "#endif\n" //
			+ "varying LOWP vec4 v_color;\n" //
			+ "varying vec2 v_texCoords;\n" //
			+ "uniform sampler2D u_texture;\n" //
			+ "void main()\n"//
			+ "{\n" //
			+ "  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n" //
			+ "}";
	```
	- Image
	```java
	//img = new Texture("badlogic.jpg");
	
	
	public Texture (TextureData data) {
		super(GL20.GL_TEXTURE_2D, Gdx.gl.glGenTexture());
		load(data);
		if (data.isManaged()) addManagedTexture(Gdx.app, this);
	}
	
	public void load (TextureData data) {
		if (this.data != null && data.isManaged() != this.data.isManaged())
			throw new GdxRuntimeException("New data must have the same managed status as the old data");
		this.data = data;

		if (!data.isPrepared()) data.prepare();

		bind();
		uploadImageData(GL20.GL_TEXTURE_2D, data);

		setFilter(minFilter, magFilter);
		setWrap(uWrap, vWrap);
		Gdx.gl.glBindTexture(glTarget, 0);
	}
	```
	
	## 
	```java
	@Override
	public void begin () {
		if (drawing) throw new IllegalStateException("SpriteBatch.end must be called before begin.");
		renderCalls = 0;

		Gdx.gl.glDepthMask(false);
		//着色器程序不为null.
		if (customShader != null)
			customShader.begin();
		else
			shader.begin();
		setupMatrices();

		drawing = true;
	}
	```
  
  
  ```java
  @Override
	public void draw (Texture texture, float x, float y, float width, float height) {
		if (!drawing) throw new IllegalStateException("SpriteBatch.begin must be called before draw.");

		float[] vertices = this.vertices;

		if (texture != lastTexture)
			switchTexture(texture);
		else if (idx == vertices.length) //
			flush();

		final float fx2 = x + width;
		final float fy2 = y + height;
		final float u = 0;
		final float v = 1;
		final float u2 = 1;
		final float v2 = 0;

		float color = this.color;
		int idx = this.idx;
		vertices[idx++] = x;
		vertices[idx++] = y;
		vertices[idx++] = color;
		vertices[idx++] = u;
		vertices[idx++] = v;

		vertices[idx++] = x;
		vertices[idx++] = fy2;
		vertices[idx++] = color;
		vertices[idx++] = u;
		vertices[idx++] = v2;

		vertices[idx++] = fx2;
		vertices[idx++] = fy2;
		vertices[idx++] = color;
		vertices[idx++] = u2;
		vertices[idx++] = v2;

		vertices[idx++] = fx2;
		vertices[idx++] = y;
		vertices[idx++] = color;
		vertices[idx++] = u2;
		vertices[idx++] = v;
		this.idx = idx;
	}
	```
  

  
  
  
## 日志  日志感觉没啥可以说的
```java
@Override
public void log (String tag, String message) {
	if (logLevel >= LOG_INFO) {
		System.out.println(tag + ": " + message);
	}
}
```

## Gdx.files.internal("");
```java
public FileHandle internal (String path) {
	return new LwjglFileHandle(path, FileType.Internal);
}

public LwjglFileHandle (String fileName, FileType type) {
	super(fileName, type);
}

protected FileHandle (String fileName, FileType type) {
	this.type = type;
	file = new File(fileName);
}

直接调用了IO
```


```java
@Override
public FileHandle external (String path) {
	return new LwjglFileHandle(path, FileType.External);
}

public LwjglFileHandle (String fileName, FileType type) {
	super(fileName, type);
}

protected FileHandle (String fileName, FileType type) {
	this.type = type;
	file = new File(fileName);
}
```
 
三种方法实现的是一样的。



## 声明周期方法
他们本身实现ApplicationListener就是一个接口



## TextureRegion
```java
public TextureRegion (Texture texture) {
	if (texture == null) throw new IllegalArgumentException("texture cannot be null.");
	this.texture = texture;
	setRegion(0, 0, texture.getWidth(), texture.getHeight());
}
```


## Actor
他在父类是没有任何实现的
  
  
  
  
  
  
  
  
  
  
  
  
  