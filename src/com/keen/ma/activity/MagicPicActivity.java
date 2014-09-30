package com.keen.ma.activity;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.aviary.android.feather.FeatherActivity;
import com.aviary.android.feather.library.Constants;
import com.aviary.android.feather.library.utils.DecodeUtils;
import com.aviary.android.feather.library.utils.ImageSizes;
import com.keen.ma.MyApplication;
import com.keen.ma.R;
import com.keen.ma.utils.AppConstants;
import com.keen.ma.utils.ToolUtils;


/**
 * ħ����Ƭҳ��
 * @author KeenW
 * @lastChangeTime 2014-8-23����10:55:01
 */
public class MagicPicActivity extends BaseActivity implements OnCheckedChangeListener{

	private static final int LOCAL_IMAGE_REQUESTCODE = 100;
	private static final int CAMERA_IAMGE_REQUESTCODE = 101;
	private static final int AVIARY_RETURNED_PICTURE = 102;
	
	private ImageView showImageView;
	private RadioGroup selectImageRG;
	private MyApplication myApp = null;
	private String currentIamgePath = "";
	private Bitmap currentBitmap = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myApp = (MyApplication)getApplication();
		myApp.addActivity(this);
		setContentLayout(R.layout.activity_magic_pic);
		setTitleText("ħ����Ƭ");
		setRightIamgeViewShow();
		
		selectImageRG = (RadioGroup)findViewById(R.id.select_pic_radio);
		selectImageRG.setOnCheckedChangeListener(this);
		showImageView = (ImageView)findViewById(R.id.show_imageview);
		setRightImageViewResource(R.drawable.crop_icon);
		currentIamgePath = AppConstants.SDCARD_BATH_PATH+"/FEI_Test/1.jpg";
		currentBitmap = BitmapFactory.decodeFile(currentIamgePath);
		setRightIamgeViewListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent newIntent = new Intent(MagicPicActivity.this, FeatherActivity.class );
				newIntent.setData( Uri.parse(currentIamgePath) );
				newIntent.putExtra( Constants.EXTRA_IN_API_KEY_SECRET, AppConstants.AVIARY_SECRET );
				startActivityForResult( newIntent, AVIARY_RETURNED_PICTURE );
			}
		});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.select_profile:
			currentIamgePath = AppConstants.SDCARD_BATH_PATH+"/FEI_Test/1.jpg";
			currentBitmap = BitmapFactory.decodeFile(currentIamgePath);
			showImageView.setImageBitmap(currentBitmap); 
			Toast.makeText(MagicPicActivity.this, "ѡ�����ҵ�ͷ��", Toast.LENGTH_SHORT).show();
			break;
		case R.id.select_local_image:
			//����ȡͼ
			Intent intent = new Intent();  
            intent.setType("image/*");  /* ����Pictures����Type�趨Ϊimage */  
            intent.setAction(Intent.ACTION_GET_CONTENT); /* ʹ��Intent.ACTION_GET_CONTENT���Action */   
            startActivityForResult(intent, LOCAL_IMAGE_REQUESTCODE);
			break;
			
		case R.id.select_camera_image:
			//���ȡͼ
			Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraIntent, CAMERA_IAMGE_REQUESTCODE);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {  
			Uri uri = data.getData();  
			if(requestCode == LOCAL_IMAGE_REQUESTCODE && !ToolUtils.isNullString(uri.toString())){
	            Log.e("uri", "================================"+uri.toString());  
	            ContentResolver cr = getContentResolver();  
	            try {  
	                /* ��Bitmap�趨��ImageView */  
	            	Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
	            	currentBitmap = bitmap;
	            	showImageView.setImageBitmap(currentBitmap);  
	                //�õ���Ƭ����
	                Cursor cursor = cr.query(uri, null, null, null, null);
	                cursor.moveToFirst();
	               // String imgNo = cursor.getString(0); //ͼƬ���
	                String imgPath = cursor.getString(1); //ͼƬ�ļ�·��
	               // String imgSize = cursor.getString(2); //ͼƬ��С
	               // String imgName = cursor.getString(3); //ͼƬ�ļ���
	                
	                currentIamgePath = imgPath;
	            } catch (FileNotFoundException e) {  
	                Log.e("Exception", e.getMessage(),e);  
	            }  
			}else if(requestCode == CAMERA_IAMGE_REQUESTCODE){
				//������ս������
				if(uri == null){
					Bundle bundle = data.getExtras();
					if(bundle != null){
						Bitmap bitmap = (Bitmap)bundle.get("data");
						 showImageView.setImageBitmap(bitmap); 
						 currentBitmap = bitmap;
						 //currentIamgePath = 
					}
				}else{
					Toast.makeText(this, "����ͼƬ��ȡʧ�ܣ�������...", Toast.LENGTH_SHORT).show();
					return;
				}
			}else if(requestCode == AVIARY_RETURNED_PICTURE){
				// output image path
                Uri mImageUri = data.getData();
                Bundle extra = data.getExtras();
                    if( null != extra ) {
                        // image has been changed by the user?
                       // boolean changed = extra.getBoolean ( Constants.EXTRA_OUT_BITMAP_CHANGED );
                        ImageSizes sizes = new ImageSizes();
                        Bitmap  bitmap = DecodeUtils.decode( MagicPicActivity.this, mImageUri, 100, 100, sizes );
						 showImageView.setImageBitmap(bitmap); 
                    }

				Toast.makeText(this, "ͼƬ��ȡʧ�ܣ�������...", Toast.LENGTH_SHORT).show();
				return;
			}
        }  
		super.onActivityResult(requestCode, resultCode, data);
	}
}
