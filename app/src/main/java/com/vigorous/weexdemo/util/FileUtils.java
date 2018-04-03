package com.vigorous.weexdemo.util;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * Created by jerry on 16/4/19 16:22
 */
public class FileUtils {
    private static final String TAG = "FileUtils";


    public static boolean deleteFile(File file) {
        if (file.exists()) {
            final File toDelete = new File(file.getAbsolutePath() + System.currentTimeMillis());
            file.renameTo(toDelete);
            return toDelete.delete();
        } else {
            return true;
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            Log.e(TAG, "wjyPatch [deleteDir] " + dir.getAbsolutePath() + " is a directory~");
            String[] children = dir.list();
            if (children == null) {
                return false;
            }
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        } else {
            Log.e(TAG, "wjyPatch [deleteDir] " + dir.getAbsolutePath() + " is NOT a directory!");
        }
        // 目录此时为空，可以删除
        return deleteFile(dir);
//        return dir.delete();
    }


    /**
     * 复制文件
     *
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     */
    public static void copyFile(File sourceFile, File targetFile)
            throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    /**
     * 复制文件夹
     *
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir)
            throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new
                        File(new File(targetDir).getAbsolutePath()
                        + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + File.separator + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + File.separator + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }


    /**
     * 从assets中copy文件或文件夹
     *
     * @param ctx
     * @param assetsPath assets中的相对路径
     * @param targetPath 目的路径的上层文件夹
     */
    public static boolean copyFileOrDirFromAssets(Context ctx, String assetsPath, String targetPath) {
        AssetManager assetManager = ctx.getAssets();
        String assets[] = null;
        try {
            assets = assetManager.list(assetsPath);
            if (assets.length == 0) {
                copyAssetsFile(ctx, assetsPath, targetPath + File.separator + assetsPath);
            } else {
                String fullTargetPath = targetPath + File.separator + assetsPath;
                File dir = new File(fullTargetPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                for (int i = 0; i < assets.length; ++i) {
                    copyFileOrDirFromAssets(ctx, assetsPath + File.separator + assets[i], targetPath);
                }
            }
            return true;
        } catch (IOException ex) {
            Log.e("wy", "copyFileOrDirFromAssets I/O Exception: " + ex.getMessage());
            return false;
        }
    }

    public static void copyAssetsFile(Context ctx, String assetsFileName, String targetPath) throws IOException {
        AssetManager assetManager = ctx.getAssets();

        InputStream in = null;
        OutputStream out = null;
        in = assetManager.open(assetsFileName);
        out = new FileOutputStream(targetPath);

        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        in = null;
        out.flush();
        out.close();
        out = null;
    }

    /**
     * 移动文件夹从source到dest
     *
     * @param source
     * @param dest
     * @return
     */
    public static boolean moveFolder(String source, String dest) {
        File fileSource = new File(source);
        if (fileSource.exists()) {
            deleteDir(new File(dest));
            return fileSource.renameTo(new File(dest));
        }
        return false;
    }

    /**
     * Reads the named file, translating {@link IOException} to a
     * {@link RuntimeException} of some sort.
     *
     * @param fileName {@code non-null;} name of the file to read
     * @return {@code non-null;} contents of the file
     */
    public static byte[] readFile(String fileName) throws IOException {
        File file = new File(fileName);
        return readFile(file);
    }

    /**
     * Reads the given file, translating {@link IOException} to a
     * {@link RuntimeException} of some sort.
     *
     * @param file {@code non-null;} the file to read
     * @return {@code non-null;} contents of the file
     * @throws IOException
     */
    public static byte[] readFile(File file) throws IOException {
        if (!file.exists()) {
            throw new RuntimeException(file + ": file not found");
        }

        if (!file.isFile()) {
            throw new RuntimeException(file + ": not a file");
        }

        if (!file.canRead()) {
            throw new RuntimeException(file + ": file not readable");
        }

        long longLength = file.length();
        int length = (int) longLength;
        if (length != longLength) {
            throw new RuntimeException(file + ": file too long");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream(length);

        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[8192];
            int bytesRead = 0;
            while ((bytesRead = in.read(buffer)) > 0) {
                baos.write(buffer, 0, bytesRead);
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    // ignored.
                }
            }
        }

        return baos.toByteArray();
    }

    /**
     * Get real file path by given uri.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @return file path
     */
    public static String getPath(final Context context, final Uri uri) {
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri =
                    ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] { split[1] };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * Get the value of the filename for this Uri.
     *
     * @param context The context.
     * @param uri The uri to check filename.
     * @return filename from given uri.
     */
    public static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf(File.separator);
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    /**
     * Read file size.
     */
    public static String readFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups))
            + " "
            + units[digitGroups];
    }

    /*
     * Get the extension of a file in lowercase.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    /**
     * @param fileName
     * @return
     * @Description:从assets 文件夹中获取文件并读取数据
     */
    public static String getFromAssets(Context context, String fileName, String seperator) {
        String result = "";
        BufferedReader br = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(
                    context.getResources().getAssets().open(fileName)));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                if (seperator != null) {
                    stringBuilder.append(seperator);
                }
            }
            result = stringBuilder.toString();
            if (seperator != null && result.length() > seperator.length()) {
                result = result.substring(0, result.length() - seperator.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (Exception e1) {
                }

            }
        }
        return result;
    }
}
