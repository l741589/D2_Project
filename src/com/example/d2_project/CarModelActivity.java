package com.example.d2_project;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;

public class CarModelActivity extends RendererActivity {
	private Object3dContainer objModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_3dcar);
		FrameLayout fl=(FrameLayout)findViewById(R.id.FrameLayout1);
		fl.addView(_glSurfaceView);
		View back=findViewById(R.id.back);
		if (back!=null){
			back.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
	}

	@Override
	public void initScene() {
		
		scene.lights().add(new Light());
		IParser parser = Parser.createParser(Parser.Type.OBJ,
				getResources(), "com.example.d2_project:raw/camaro_obj", false);
		parser.parse();

		objModel = parser.getParsedObject();
		objModel.scale().x = objModel.scale().y = objModel.scale().z = .7f;
		scene.addChild(objModel);
		objModel.rotation().x=120;
		objModel.rotation().y=180;
		objModel.position().y-=0.3;
	}

	@Override
	public void updateScene() {
		//objModel.rotation().x++;
		//objModel.rotation().y++;
		objModel.rotation().z++;
	}
}