package com.keen.ma.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.keen.ma.R;

/**
 * ������ͼҳ���GridView������
 * @author KeenW
 * @lastChangeTime 2014-8-20����10:23:35
 */
public class WallPaperGridViewAdapter extends BaseAdapter {

	private Context context;
	private int selectPosition = -1;
	private int[] picIds = {R.drawable.portrait_bg1,R.drawable.portrait_bg2,
			R.drawable.portrait_bg3,R.drawable.portrait_bg4,
			R.drawable.portrait_bg5,R.drawable.portrait_bg6,
			R.drawable.portrait_bg7,R.drawable.portrait_bg8,
			R.drawable.eye_icon,};
	private Bitmap currentBitmap = null;
	
	public WallPaperGridViewAdapter(Context context) {
		this.context = context;
	}
	
	public void setSelectedPosition(int position){
		this.selectPosition = position;
	}

	public int getSelectedPosition(){
		return selectPosition;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_change_wallpaper, null);
			viewHolder = new ViewHolder();
			viewHolder.wallpaperImageView = (ImageView)convertView.findViewById(R.id.wallpaper_imageview);
			viewHolder.wallpaperView = (View)convertView.findViewById(R.id.wallpaper_view);
			convertView.setTag(viewHolder);
		}else
			viewHolder = (ViewHolder) convertView.getTag();
		
		/*####################�� ����ֹ�������#########################*/
		//����취����ѹ��
		
		//ѹ�������ڽ�ʡBITMAP�ڴ�ռ�--���BUG�Ĺؼ����� 
		/*BitmapFactory.Options opts = new BitmapFactory.Options(); 
		opts.inSampleSize = 4; //�����ֵѹ���ı�����2��������������ֵԽС��ѹ����ԽС��ͼƬԽ���� 
		//����ԭͼ����֮���bitmap���� 
		currentBitmap = BitmapFactory.decodeResource(context.getResources(), picIds[position], opts);*/
		if(position == 8){
			convertView.setEnabled(false);
		}
		currentBitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeResource(context.getResources(), picIds[position]), 130, 180);
		viewHolder.wallpaperImageView.setImageBitmap(currentBitmap);

		if(position == selectPosition){
			viewHolder.wallpaperView.setBackgroundColor(context.getResources().getColor(R.color.trans_light_green));
		}else{
			viewHolder.wallpaperView.setBackgroundColor(Color.TRANSPARENT);
		}
		
			convertView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setSelectedPosition(position);
					notifyDataSetChanged();
				}
			});
		
		return convertView;
	}

	public class ViewHolder {
		ImageView wallpaperImageView;
		View wallpaperView;
	}

}
