package pagersample.rajesh.com.viewpagersample;


import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PagerAdapter extends android.support.v4.view.PagerAdapter {

	private Activity _activity;
	private ArrayList<DashboardResponse> _imagePaths;
	private LayoutInflater inflater;


	public PagerAdapter(Activity activity,
						ArrayList<DashboardResponse> imagePaths) {

		this._activity = activity;
		this._imagePaths = imagePaths;
	}

	@Override
	public int getCount() {
		return this._imagePaths.size();
	}

	@Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
	
	@Override
    public Object instantiateItem(ViewGroup container, int position) {

        TextView tvTitle;
        TextView tvContent;
		ImageView ivUserImage;
 
        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.row_pager_list, container,
                false);
 
        ivUserImage = (ImageView) viewLayout.findViewById(R.id.ivIconActor);
        tvTitle = (TextView) viewLayout.findViewById(R.id.tvActorName);
		tvContent = (TextView) viewLayout.findViewById(R.id.tvActorDetails);


		tvTitle.setText(_imagePaths.get(position).getName());
		tvContent.setText(_imagePaths.get(position).getDescription());


		Picasso.with(_activity)
				.load(_imagePaths.get(position).getImage())
				.placeholder(R.mipmap.ic_launcher)
				.error(R.mipmap.ic_launcher)
				.centerCrop()
				.fit()
				.into(ivUserImage);


		((ViewPager) container).addView(viewLayout);
 
        return viewLayout;
	}
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
 
    }

}
