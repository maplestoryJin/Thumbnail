package plc.jtr.com.thumbnail;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Bitmap> list = new ArrayList<>();
    private String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        if (gridView != null) {
            gridView.setAdapter(new MyAdapter());
        }
//        BitmapFactory.Options options=new BitmapFactory.Options();
//        options.inSampleSize = 1;
//        Bitmap curThumb = MediaStore.Video.Thumbnails.getThumbnail(crThumb, id, MediaStore.Video.Thumbnails.MICRO_KIND, options);
        ArrayList<HashMap<String, Object>> allPicture = getAllPicture(this);

        for (HashMap<String, Object> pic : allPicture) {
            Set<Map.Entry<String, Object>> entries = pic.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                System.out.println("key = "+ entry.getKey() + ", value = " + entry.getValue());
                if ("video_id".equals(entry.getKey())) {
                    Bitmap bitmap = ThumbnailUtils.createVideoThumbnail((String) entry.getValue(), MediaStore.Video.Thumbnails.MINI_KIND);
                    list.add(bitmap);
                }
                if ("video_duration".equals(entry.getKey())) {
                    long duration = (long) entry.getValue();
                    duration = 8888888888L;
                    long seconds = duration / 1000;
                    format = DateUtils.formatElapsedTime(new StringBuilder(8), seconds);

                }
            }
        }
    }

    private ArrayList<HashMap<String,Object>> getAllPicture(Context context) {
        ArrayList<HashMap<String,Object>> picturemaps = new ArrayList<>();
        HashMap<String,Object> picturemap;
        ContentResolver crThumb = context.getContentResolver();
//        Cursor cursor = crThumb.query(
//                MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
//                new String[]{
//                        MediaStore.Video.Thumbnails.VIDEO_ID,
//                        MediaStore.Video.Thumbnails.DATA
//                },
//                null,
//                null,
//                null);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                picturemap = new HashMap<>();
//                picturemap.put("video_id_path",cursor.getInt(0)+"");
//                picturemap.put("thumbnail_path",cursor.getString(1));
//                picturemaps.add(picturemap);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }

        //再得到正常图片的path
//        for (int i = 0;i<picturemaps.size();i++) {
//            picturemap = picturemaps.get(i);
//            String media_id = (String) picturemap.get("video_id_path");
            Cursor cursor = crThumb.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    new String[]{
                            MediaStore.Video.Media.DATA,
                            MediaStore.Video.Media.DURATION,
                            MediaStore.Video.Media.DATE_TAKEN,
                    },
                    null,
//                    MediaStore.Video.Media._ID+"="+media_id,
                    null,
                    null
            );
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    picturemap = new HashMap<>();
                    picturemap.put("video_id",cursor.getString(0));
                    picturemap.put("video_duration", cursor.getLong(1));
                    picturemaps.add(picturemap);
                } while (cursor.moveToNext());
                cursor.close();
            }
//        }

        return picturemaps;
    }


    /**
     * 根据指定的图像路径和大小来获取缩略图
     * 此方法有两点好处：
     *     1. 使用较小的内存空间，第一次获取的bitmap实际上为null，只是为了读取宽度和高度，
     *        第二次读取的bitmap是根据比例压缩过的图像，第三次读取的bitmap是所要的缩略图。
     *     2. 缩略图对于原图像来讲没有拉伸，这里使用了2.2版本的新工具ThumbnailUtils，使
     *        用这个工具生成的图像不会被拉伸。
     * @param imagePath 图像的路径
     * @param width 指定输出图像的宽度
     * @param height 指定输出图像的高度
     * @return 生成的缩略图
     */
    private Bitmap getImageThumbnail(String imagePath, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.show);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.text_time);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.imageView.setImageBitmap(list.get(position));
            viewHolder.textView.setText(format);

            return convertView;
        }

    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
