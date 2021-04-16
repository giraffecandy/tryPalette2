package app.babachan.trypalette2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        @InjectView(R.id.listView)
        ListView mListView;
        @InjectView(R.id.view1)
        View mView1;
        @InjectView(R.id.view2)
        View mView2;
        @InjectView(R.id.view3)
        View mView3;
        @InjectView(R.id.view4)
        View mView4;
        @InjectView(R.id.view5)
        View mView5;
        @InjectView(R.id.view6)
        View mView6;
        @InjectView(R.id.vibrantTitleView)
        TextView mVibrantTitleView;
        @InjectView(R.id.vibrantText)
        TextView mVibrantText;
        @InjectView(R.id.vibrantContaienr)
        LinearLayout mVibrantContaienr;
        @InjectView(R.id.mutedTitleView)
        TextView mMutedTitleView;
        @InjectView(R.id.mutedText)
        TextView mMutedText;
        @InjectView(R.id.mutedContaienr)
        LinearLayout mMutedContaienr;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my);
            ButterKnife.inject(this);


            TypedArray images = getResources().obtainTypedArray(R.array.big_array_drawable);
            List<Drawable> drawables = new ArrayList<Drawable>();
            for (int i = 0; i < images.length(); i++) {
            drawables.add(images.getDrawable(i));
        }

            ImageListAdapter imageListAdapter = new ImageListAdapter(this, 0, drawables);
            mListView.setAdapter(imageListAdapter);

            onClickImageList(0);
        }

        @OnItemClick(R.id.listView)
        void onClickImageList(int position) {
            Drawable drawable = (Drawable) mListView.getItemAtPosition(position);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    if  (palette != null) {
                        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                        Palette.Swatch mutedSwatch = palette.getMutedSwatch();

                        if (vibrantSwatch != null) {
                            mVibrantContaienr.setBackgroundColor(vibrantSwatch.getRgb());
                            mVibrantTitleView.setTextColor(vibrantSwatch.getTitleTextColor());
                            mVibrantText.setTextColor(vibrantSwatch.getBodyTextColor());
                        }

                        if (mutedSwatch != null) {
                            mMutedContaienr.setBackgroundColor(mutedSwatch.getRgb());
                            mMutedTitleView.setTextColor(mutedSwatch.getTitleTextColor());
                            mMutedText.setTextColor(mutedSwatch.getBodyTextColor());
                        }

                        mView1.setBackgroundColor(palette.getLightVibrantColor(R.color.background_material_light));
                        mView2.setBackgroundColor(palette.getVibrantColor(R.color.background_material_light));
                        mView3.setBackgroundColor(palette.getDarkVibrantColor(R.color.background_material_dark));
                        mView4.setBackgroundColor(palette.getLightMutedColor(R.color.background_material_light));
                        mView5.setBackgroundColor(palette.getMutedColor(R.color.background_material_light));
                        mView6.setBackgroundColor(palette.getDarkMutedColor(R.color.background_material_dark));
                    }
                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.my, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}