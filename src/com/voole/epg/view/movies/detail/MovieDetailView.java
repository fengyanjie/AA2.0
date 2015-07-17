package com.voole.epg.view.movies.detail;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils.TruncateAt;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.ShadeButton;
import com.voole.epg.corelib.model.account.Product;
import com.voole.epg.corelib.model.movies.Content;
import com.voole.epg.corelib.model.movies.Detail;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.tvutils.ImageManager;

public class MovieDetailView extends BaseLinearLayout {
	private ImageView poster = null;
	private TextView text_suggest = null;

	private TextView text_price = null;
	private TextView text_barginPrice = null;
	private AlwaysMarqueeTextView text_filmName = null;
	private TextView text_year_mins_area_type = null;
	private TextView text_introduce = null;
	private AlwaysMarqueeTextView text_director = null;
	private AlwaysMarqueeTextView text_actor = null;
	private AlwaysMarqueeTextView text_order = null;
	private ShadeButton leftBtn = null;
	private ShadeButton middleBtn = null;
	private ShadeButton rightBtn = null;
	private ImageView loveImg = null;
	
	private RelativeLayout layout_btn=null;
	
	private boolean isPursue = true;

	private final int textSize = DisplayManager.GetInstance()
			.changeTextSize(26);

	
	
	private Film film = null;
	private List<Content> contents = null;

	private DetailButtonListener listener = null;

	public void setDetailButtonListener(DetailButtonListener listener) {
		this.listener = listener;
	}

	private ButtonStatus currentBtnStatus = ButtonStatus.play;

	public enum ButtonStatus {
		play, recommend, selected
	}

	public MovieDetailView(Context context) {
		super(context);
		init(context);
	}

	public MovieDetailView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MovieDetailView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * left : right --- 1:3
	 * 
	 * @param context
	 */
	private void init(Context context) {
		setOrientation(HORIZONTAL);
		initLeft(context);
		initRight(context);
	}

	/**
	 * up : down --- 6:1
	 * 
	 * @param context
	 */
	private void initLeft(Context context) {
		LinearLayout layout_left = new LinearLayout(context);
		layout_left.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param_layout_left = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3);
		param_layout_left.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(60);
		param_layout_left.topMargin = DisplayManager.GetInstance()
				.changeWidthSize(15);
		layout_left.setLayoutParams(param_layout_left);
		poster = new ImageView(context);
		LinearLayout.LayoutParams param_poster = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_poster.topMargin=DisplayManager.GetInstance().changeHeightSize(20);
		poster.setLayoutParams(param_poster);
		poster.setScaleType(ScaleType.FIT_XY);
		layout_left.addView(poster);

		text_suggest = new TextView(context);
		LinearLayout.LayoutParams param_text_suggest = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 6);
//		param_text_suggest.topMargin = -20;
		text_suggest.setPadding(0, 10, 0, 0);
		text_suggest.setGravity(Gravity.CENTER_HORIZONTAL);
		text_suggest.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(22));
		text_suggest.setTextColor(Color.GRAY);
		text_suggest.setLayoutParams(param_text_suggest);
		text_suggest.setBackgroundResource(R.drawable.haibao_dao);
		layout_left.addView(text_suggest);

		addView(layout_left);
	}

	private void initRight(Context context) {
		RelativeLayout layout_right = new RelativeLayout(context);
		LinearLayout.LayoutParams param_layout_right = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_layout_right.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(25);
		param_layout_right.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(53);
		layout_right.setLayoutParams(param_layout_right);

		RelativeLayout layout_price = new RelativeLayout(context);
		layout_price.setId(1001);
		RelativeLayout.LayoutParams param_layout_price = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_layout_price.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		layout_price.setLayoutParams(param_layout_price);

		text_barginPrice = new TextView(context);
		text_barginPrice.setId(10011);
		text_barginPrice.setTextColor(Color.parseColor("#00c1ea"));
		text_barginPrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize + 4);
		RelativeLayout.LayoutParams param_text_barginPrice = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_text_barginPrice.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		text_barginPrice.setLayoutParams(param_text_barginPrice);
		layout_price.addView(text_barginPrice);

		TextView barginPrice = new TextView(context);
		barginPrice.setText("促销价:");
		barginPrice.setId(10012);
		barginPrice.setTextColor(Color.GRAY);
		barginPrice
				.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(22));
		RelativeLayout.LayoutParams param_barginPrice = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_barginPrice.addRule(RelativeLayout.LEFT_OF, 10011);
		param_barginPrice.addRule(RelativeLayout.ALIGN_BOTTOM, 10011);
		barginPrice.setLayoutParams(param_barginPrice);
		layout_price.addView(barginPrice);

		text_price = new TextView(context);
		text_price.setId(10013);
		text_price.setTextColor(Color.GRAY);
		text_price.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(22));
		text_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		RelativeLayout.LayoutParams param_text_price = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_text_price.addRule(RelativeLayout.LEFT_OF, 10012);
		param_text_price.addRule(RelativeLayout.ALIGN_BOTTOM, 10012);
		param_text_price.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(5);
		text_price.setLayoutParams(param_text_price);
		layout_price.addView(text_price);

		TextView price = new TextView(context);
		price.setText("原价：");
		price.setTextColor(Color.GRAY);
		price.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(22));
		RelativeLayout.LayoutParams param_price = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_price.addRule(RelativeLayout.LEFT_OF, 10013);
		param_price.addRule(RelativeLayout.ALIGN_BOTTOM, 10013);
		price.setLayoutParams(param_price);
		layout_price.addView(price);

		layout_right.addView(layout_price);

		text_filmName = new AlwaysMarqueeTextView(context);
		text_filmName.setId(1002);
		text_filmName.setMarquee(true);
		text_filmName.setTextColor(Color.WHITE);
		text_filmName.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(
				42));
		RelativeLayout.LayoutParams param_text_filmName = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_text_filmName.addRule(RelativeLayout.BELOW, 1001);
		param_text_filmName.bottomMargin = DisplayManager.GetInstance()
				.changeHeightSize(5);
		param_text_filmName.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(30);
		param_text_filmName.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(30);
		text_filmName.setLayoutParams(param_text_filmName);
		layout_right.addView(text_filmName);

		text_year_mins_area_type = new TextView(context);
		text_year_mins_area_type.setId(1003);
		text_year_mins_area_type.setTextColor(Color.WHITE);
		text_year_mins_area_type.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		RelativeLayout.LayoutParams param_text_year_mins_area_type = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_text_year_mins_area_type.addRule(RelativeLayout.BELOW, 1002);
		param_text_year_mins_area_type.bottomMargin = DisplayManager
				.GetInstance().changeHeightSize(5);
		text_year_mins_area_type
				.setLayoutParams(param_text_year_mins_area_type);
		layout_right.addView(text_year_mins_area_type);

		text_introduce = new TextView(context);
		text_introduce.setTextColor(Color.WHITE);
		text_introduce.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		text_introduce.setEllipsize(TruncateAt.END);
		text_introduce.setMaxLines(3);
		text_introduce.setLineSpacing(3f, 1f);
		RelativeLayout.LayoutParams param_text_introduce = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_text_introduce.addRule(RelativeLayout.BELOW, 1003);
		// param_text_introduce.addRule(RelativeLayout.ABOVE, 1004);
		// param_text_introduce.bottomMargin =
		// DisplayManager.GetInstance().changeHeightSize(4);
		text_introduce.setLayoutParams(param_text_introduce);
		layout_right.addView(text_introduce);

		text_director = new AlwaysMarqueeTextView(context);
		text_director.setId(1004);
		text_director.setTextColor(Color.WHITE);
		text_director.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		text_director.setMarquee(false);
		RelativeLayout.LayoutParams param_text_director = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_text_director.addRule(RelativeLayout.ABOVE, 1005);
		param_text_director.bottomMargin = DisplayManager.GetInstance()
				.changeHeightSize(4);
		text_director.setLayoutParams(param_text_director);
		layout_right.addView(text_director);

		text_actor = new AlwaysMarqueeTextView(context);
		text_actor.setId(1005);
		text_actor.setTextColor(Color.WHITE);
		text_actor.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		text_director.setMarquee(false);
		RelativeLayout.LayoutParams param_text_actor = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_text_actor.addRule(RelativeLayout.ABOVE, 1006);
		param_text_actor.bottomMargin = DisplayManager.GetInstance()
				.changeHeightSize(4);
		text_actor.setLayoutParams(param_text_actor);
		layout_right.addView(text_actor);

		text_order = new AlwaysMarqueeTextView(context);
		text_order.setMarquee(true);
		text_order.setVisibility(View.INVISIBLE);
		text_order.setId(1006);
		text_order.setTextColor(Color.parseColor("#00c1ea"));
		text_order.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		text_order.setText("订购情况：正在获取订购情况。");
		RelativeLayout.LayoutParams param_text_order = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_text_order.addRule(RelativeLayout.ABOVE, 1007);
		param_text_order.bottomMargin = DisplayManager.GetInstance()
				.changeHeightSize(10);
		text_order.setLayoutParams(param_text_order);
		layout_right.addView(text_order);

		layout_btn = new RelativeLayout(context);
		layout_btn.setId(1007);
		RelativeLayout.LayoutParams param_layout_btn = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_layout_btn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		param_layout_btn.addRule(RelativeLayout.CENTER_HORIZONTAL);
		param_layout_btn.bottomMargin = DisplayManager.GetInstance()
				.changeHeightSize(20);
		layout_btn.setLayoutParams(param_layout_btn);

		leftBtn = new ShadeButton(context);
		leftBtn.setTextSize(textSize);
		leftBtn.setId(ID.MovieDetaiView.DETAIL_LEFT_BTN_ID);
		leftBtn.setNextFocusUpId(ID.LogoView.SEARCH_ID);
		// leftBtn.setTextColor(Color.WHITE);
		// leftBtn.setBackgroundResource(R.drawable.cs_movie_detail_button_unfocuse);
		leftBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null && film != null) {
					if (currentBtnStatus == ButtonStatus.play) {
						listener.onPlay(film);
					} else if (currentBtnStatus == ButtonStatus.recommend) {
						listener.onRecommend();
						setButtonStatus(ButtonStatus.selected);
					} else if (currentBtnStatus == ButtonStatus.selected) {
						listener.onSelected();
						setButtonStatus(ButtonStatus.recommend);
					}
				}
			}
		});
		RelativeLayout.LayoutParams param_leftBtn = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_leftBtn.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_leftBtn.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(45);
		leftBtn.setLayoutParams(param_leftBtn);
		layout_btn.addView(leftBtn);

		middleBtn = new ShadeButton(context);
		middleBtn.setTextSize(textSize);
		middleBtn.setNextFocusUpId(ID.LogoView.SEARCH_ID);
		// middleBtn.setTextColor(Color.WHITE);
		middleBtn.setText("收藏");
		// middleBtn.setBackgroundResource(R.drawable.cs_movie_detail_button_unfocuse);
		middleBtn.setId(10073);
		middleBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null && film != null) {
					listener.onFavorite(film);
				}
			}
		});
		RelativeLayout.LayoutParams param_middleBtn = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_middleBtn.addRule(RelativeLayout.RIGHT_OF, ID.MovieDetaiView.DETAIL_LEFT_BTN_ID );
		middleBtn.setLayoutParams(param_middleBtn);
		layout_btn.addView(middleBtn);

		rightBtn = new ShadeButton(context);
		rightBtn.setNextFocusUpId(ID.LogoView.SEARCH_ID);
		rightBtn.setTextSize(textSize);
		// rightBtn.setTextColor(Color.WHITE);
		rightBtn.setText("追剧");
		// rightBtn.setBackgroundResource(R.drawable.cs_movie_detail_button_unfocuse);
		rightBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null && film != null) {
					listener.onPursueVideo(film);
				}
			}
		});
		rightBtn.setId(10072);
		RelativeLayout.LayoutParams param_rightBtn = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_rightBtn.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(45);
		param_rightBtn.addRule(RelativeLayout.RIGHT_OF, 10073);
		rightBtn.setLayoutParams(param_rightBtn);
		layout_btn.addView(rightBtn);
		layout_right.addView(layout_btn);

		RelativeLayout layout_love = new RelativeLayout(context);
		layout_love.setId(1999);
		RelativeLayout.LayoutParams param_layout_lov = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_layout_lov.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layout_love.setLayoutParams(param_layout_lov);
		layout_right.addView(layout_love);

		loveImg = new ImageView(context);
		loveImg.setId(10074);
		RelativeLayout.LayoutParams param_loveImg = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_loveImg.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		loveImg.setImageResource(R.drawable.cainixihuan);
		loveImg.setLayoutParams(param_loveImg);
		layout_love.addView(loveImg);
		loveImg.setVisibility(View.VISIBLE);

		addView(layout_right);
	}

	public void setDetailData(Detail detail, Film film) {
		this.film = film;
		this.contents = detail.getContentList();
		text_filmName.setText(this.film.getFilmName());
		String str_year="";
		if(!this.film.getYear().contains("不")){
			str_year=this.film.getYear()+"年";
		}
		text_year_mins_area_type.setText(str_year + "  "
				+ this.film.getLongTime() + "分钟" + "  " + this.film.getArea());
		/*text_introduce.setText(this.film.getIntroduction()
				+ this.film.getDescription());*/
		text_introduce.setText(this.film.getIntroduction());
		text_director.setText("导演:" + this.film.getDirector());
		text_actor.setText("主演:" + this.film.getActor());
		ImageManager.GetInstance().displayImage(this.film.getImgUrlB(), poster);
		setSuggestText();
		if (contents != null && contents.size() > 1) {
			currentBtnStatus = ButtonStatus.recommend;
		}
		setButtonStatus(currentBtnStatus);
	}

	private void setSuggestText() {
		if (contents == null || contents.size() == 0) {
			return;
		}
		String rateString = contents.get(0).getRate();
		String is3D = contents.get(0).getIs3dvideo();
		int rate = Integer.parseInt(rateString);
		SpannableString sp = null;
		if (rate > 1300) {
			if ("1".equals(is3D)) {
				sp = new SpannableString("为保证流畅观看，推荐您在8M以上带宽播放");
			} else {
				sp = new SpannableString("为保证流畅观看，推荐您在4M以上带宽播放");
			}
		} else {
			if ("1".equals(is3D)) {
				sp = new SpannableString("为保证流畅观看，推荐您在4M以上带宽播放");
			} else {
				sp = new SpannableString("为保证流畅观看，推荐您在2M以上带宽播放");
				
			}
		}
//		sp.setSpan(new ForegroundColorSpan(Color.rgb(0xe4, 0x7b, 0x39)), 1, 3,
//				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		sp.setSpan(new ForegroundColorSpan(Color.parseColor("#00c1ea")), 12, 14,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		text_suggest.setText(sp);
	}

	public void setProductsData(List<Product> products) {
		if (products != null && products.size() > 0) {
			String costFee = products.get(0).getCostFee();
			String fee = products.get(0).getFee();
			if (!"".equals(costFee)) {
				text_price.setText("￥" + Integer.parseInt(costFee) / 100);
			} else {
				text_price.setText("￥" + "0");
			}
			if (!"".equals(fee)) {
				text_barginPrice.setText("￥" + Integer.parseInt(fee) / 100);
			} else {
				text_barginPrice.setText("￥" + "0");
			}
		}
	}

	public void showLove(boolean isShow) {
		if (isShow) {
			loveImg.setVisibility(View.VISIBLE);
		} else {
			loveImg.setVisibility(View.GONE);
		}

	}

	public void setCheckInfo(String checkInfoResult) {
		text_order.setVisibility(View.VISIBLE);
		text_order.setText("订购情况：" + checkInfoResult);
	}

	public void setLeftFocus() {
		if (leftBtn != null) {
			leftBtn.requestFocus();
		}
	}

	public void setIsFavorite(boolean isFavorite) {
		if (isFavorite) {
			middleBtn.setText("已收藏");
		} else {
			middleBtn.setText("收藏");
		}
	}

	public void setIsTele(boolean isTele) {
		if (isTele) {
			rightBtn.setText("已追剧");
		} else {
			rightBtn.setText("追剧");
		}
	}
	
	public void setIsPursue(boolean isPursue){
		this.isPursue = isPursue;
		if (isPursue) {
			rightBtn.setVisibility(VISIBLE);
		}else {
			rightBtn.setVisibility(INVISIBLE);
		}
	}

	private void setButtonStatus(ButtonStatus currentBtnStatus) {
		this.currentBtnStatus = currentBtnStatus;
		if (currentBtnStatus == ButtonStatus.play) {
			leftBtn.setText("播放");
			rightBtn.setVisibility(GONE);
		} else if (currentBtnStatus == ButtonStatus.recommend) {
			leftBtn.setText("推荐");
			if (isPursue) {
				rightBtn.setVisibility(VISIBLE);
			}else {
				rightBtn.setVisibility(INVISIBLE);
			}
		} else if (currentBtnStatus == ButtonStatus.selected) {
			leftBtn.setText("选集");
			if (isPursue) {
				rightBtn.setVisibility(VISIBLE);
			}else {
				rightBtn.setVisibility(INVISIBLE);
			}
		}
	}

	public void showButton(boolean isShow){
		if(isShow){
			layout_btn.setVisibility(VISIBLE);
		}else{
			layout_btn.setVisibility(INVISIBLE);
		}
		
	}
	
	public interface DetailButtonListener {
		/**
		 * 播放
		 * 
		 * @param film
		 */
		public void onPlay(Film film);

		/**
		 * 推荐
		 */
		public void onRecommend();

		/**
		 * 选集
		 */
		public void onSelected();

		/**
		 * 收藏
		 * 
		 * @param film
		 */
		public void onFavorite(Film film);

		/**
		 * 追剧
		 * 
		 * @param film
		 */
		public void onPursueVideo(Film film);
	}
}
