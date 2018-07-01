package database.kotlin.flow9.net.filebasic

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.FileObserver
import android.util.Log
import java.io.*


/**
 * Basic Process
 *
 * 1. path
 *
 * 2. stream
 *
 * 3. (buffer)
 *
 * 4. read or write
 *
 * 5. close
 *
 */
class FileUtil {

    /**
     * test internal directory
     */
    fun internalDir(context: Context) {
        try {
            // getDataDirectory
            Log.d("E.getDataDirectory.path", Environment.getDataDirectory().path)
            Log.d("E.getDataDirectory.abs", Environment.getDataDirectory().absolutePath)
            Log.d("E.getDataDirectory.cnn", Environment.getDataDirectory().canonicalPath)

            // getRootDirectory
            Log.d("E.getRootDirectory.path", Environment.getRootDirectory().path)
            Log.d("E.getRootDirectory.abs", Environment.getRootDirectory().absolutePath)
            Log.d("E.getRootDirectory.cnn", Environment.getRootDirectory().canonicalPath)

            // getDir
            Log.d("getDir.path", context.getDir(null, 0).path)
            Log.d("getDir.abs", context.getDir(null, 0).absolutePath)
            Log.d("getDir.cnn", context.getDir(null, 0).canonicalPath)

            // getFilesDir
            Log.d("getFilesDir.path", context.filesDir.path)
            Log.d("getFilesDir.abs", context.filesDir.absolutePath)
            Log.d("getFilesDir.cnn", context.filesDir.canonicalPath)

            // getCacheDir
            Log.d("getCacheDir.path", context.cacheDir.path)
            Log.d("getCacheDir.abs", context.cacheDir.absolutePath)
            Log.d("getCacheDir.cnn", context.cacheDir.canonicalPath)

        } catch (e: Exception) {

        } finally {

        }
    }

    /**
     * text external directory
     */
    fun externalDir(context: Context) {
        try {
            // getExternalStorageDir
            Log.d("E.getExStorageDir.pth",
                    Environment.getExternalStorageDirectory().path)
            Log.d("E.getExStorageDir.abs",
                    Environment.getExternalStorageDirectory().absolutePath)
            Log.d("E.getExStorageDir.cnn",
                    Environment.getExternalStorageDirectory().canonicalPath)

            // getExternalFilesDir
            Log.d("getExternalFilesDir.pth",
                    context.getExternalFilesDir(null)!!.path)
            Log.d("getExternalFilesDir.abs",
                    context.getExternalFilesDir(null)!!.absolutePath)
            Log.d("getExternalFilesDir.cnn",
                    context.getExternalFilesDir(null)!!.canonicalPath)

            // getExternalCacheDir
            Log.d("getExternalCacheDir.pth",
                    context.externalCacheDir!!.path)
            Log.d("getExternalCacheDir.abs",
                    context.externalCacheDir!!.absolutePath)
            Log.d("getExternalCacheDir.cnn",
                    context.externalCacheDir!!.canonicalPath)

            // getDownloadCacheDir
            Log.d("E.getDownldCacheDir.pth",
                    Environment.getDownloadCacheDirectory().path)
            Log.d("E.getDownldCacheDir.abs",
                    Environment.getDownloadCacheDirectory().absolutePath)
            Log.d("E.getDownldCacheDir.cnn",
                    Environment.getDownloadCacheDirectory().canonicalPath)

            // getExternalPublicDir
            Log.d("E.ExtStrPublicDir.pth",
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                            .path)
            Log.d("E.ExtStrPublicDir.pth",
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                            .absolutePath)
            Log.d("E.ExtStrPublicDir.pth",
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                            .canonicalPath)
        } catch (e: Exception) {

        } finally {

        }
    }

    /**
     * read internal storage file by FilInputStream
     */
    fun readInternalFileOne(context: Context, FILE_NAME: String) {
        var text = ""
        // 파일명으로 파일 객체 생성
        val file = File(context.filesDir, FILE_NAME)
        try {
            // FileNotFoundException 예외처리
            if (!file.exists()) {
                Log.e("readInternal1", "File Not Found!!")
                return
            }
            // FileInputStream을 통해 스트림을 열어줍니다
            val fis = FileInputStream(FILE_NAME)
            // 문자열일 경우 Reader를 사용할 수 있습니다
            val isr = InputStreamReader(fis)
            // Buffer를 통해 바이트 단위로 읽어오지 않고 덩어리 단위로 스트림을 읽어올 수 있습니다
            val br = BufferedReader(isr)
            // line 리더와 버퍼를 통해 파일의 문자열을 한 줄씩 읽습니다
            var line = br.readLine();
            while (line != null) {
                text += line
                text += "\r\n"
                line = br.readLine();
            }
            // 사용한 스트림은 반드시 닫아줍니다
            fis.close()
            isr.close()
            br.close()
        } catch (e: IOException) {
            Log.e("readInternalFileOne", e.toString())
        }
        Log.e("readInternalFileOne", text)
    }

    /**
     * read internal storage file by openFileInput
     */
    fun readInternalFileTwo(context: Context, FILE_NAME: String) {
        var text = ""
        // 경로와 파일명으로 파일 객체 생성
        val file = File(context.filesDir, FILE_NAME)
        try {
            // FileNotFoundException
            if (!file.exists()) {
                Log.e("readInternalFileTwo", "File Not Found!!")
                return
            }
            // openFileInput 을 통해 스트림을 열어줍니다
            val fis = context.openFileInput(FILE_NAME)
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)
            var line = br.readLine();
            while (line != null) {
                text += line
                text += "\r\n"
                line = br.readLine();
            }
            fis.close()
            isr.close()
            br.close()
        } catch (e: IOException) {
            Log.e("readInternalFileTwo", e.toString())
        }

        Log.e("readInternalFileTwo", text)
    }

    /**
     * read internal storage file by FileReader
     */
    fun readInternalFileThree(context: Context, FILE_NAME: String) {
        var text = ""
        val file = File(context.filesDir, FILE_NAME)
        try {
            // FileNotFoundException
            if (!file.exists()) {
                Log.e("readInternalFileThree", "File Not Found!!")
                return
            }
            // FileReader 을 통해 스트림을 열어줍니다
            val fr = FileReader(file)
            val br = BufferedReader(fr)
            var line = br.readLine();
            while (line != null) {
                text += line
                text += "\r\n"
                line = br.readLine();
            }
            fr.close()
            br.close()
        } catch (e: IOException) {
            Log.e("readInternalFileThree", e.toString())
        }

        Log.e("readInternalFileThree", text)
    }

    /**
     * read internal storage file
     */
    fun readInternalFileFour(context: Context, FILE_NAME: String) {
        var data: ByteArray? = null
        val file = File(context.filesDir, FILE_NAME)
        try {
            // FileNotFoundException
            if (!file.exists()) {
                Log.e("readInternalFileFour", "File Not Found!!")
                return
            }
            // Reader
            val fis = FileInputStream(file)
            // data
            data = ByteArray(fis.available())
            while (fis.read(data) != -1)
                fis.close()
        } catch (e: IOException) {
            Log.e("readInternalFileFour", e.toString())
        }

        Log.e("readInternalFileFour", String(data!!))
    }

    /**
     * read internal storage file
     */
    fun readInternalFileFive(context: Context, FILE_NAME: String) {
        var data: ByteArray? = null
        val file = File(context.filesDir, FILE_NAME)
        try {
            // FileNotFoundException
            if (!file.exists()) {
                Log.e("readInternalFileFive", "File Not Found!!")
                return
            }
            // Stream
            val `is` = context.assets.open("test.txt")
            // data
            data = ByteArray(`is`.available())
            while (`is`.read(data) != -1)
                `is`.close()
        } catch (e: IOException) {

        }

        Log.e("readInternalFileFive", String(data!!))
    }

    /**
     * write at internal storage by FileOutputStream
     */
    fun writeFileInternalOne(FILE_NAME: String, content: String) {
        try {
            // FileOutputStream 을 통해 스트림을 열어줍니다
            val fos = FileOutputStream(FILE_NAME)
            // 문자열일 경우 Writer를 통해 읽어올 수 있습니다
            val writer = OutputStreamWriter(fos)
            // Buffer를 통해 바이트 단위로 읽어오지 않고 덩어리 단위로 스트림을 읽어올 수 있습니다
            val bw = BufferedWriter(writer)
            // 인자로 받아온 content 쓰기
            bw.write(content)
            // 스트림은 사용 후 반드시 닫아줍니다
            bw.close()
        } catch (e: IOException) {

        }

    }

    /**
     * write at internal storage by openFileOutput
     */
    fun writeFileInternalTwo(context: Context, FILE_NAME: String, content: String) {
        try {
            // openFileOutput 을 통해 스트림을 열어줍니다
            val fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            val writer = OutputStreamWriter(fos)
            val bw = BufferedWriter(writer)
            bw.write(content)
            bw.flush()
            bw.close()
        } catch (e: IOException) {

        }

    }

    /**
     * write at internal storage by FileWriter
     */
    fun writeFileInternalThree(context: Context, FILE_NAME: String, content: String) {
        val fileWriter: FileWriter
        val file = File(context.filesDir, FILE_NAME)
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
            // FileWriter 을 통해 스트림을 열어줍니다
            fileWriter = FileWriter(file, true)
            fileWriter.write(content)
            fileWriter.flush()
            fileWriter.close()
        } catch (e: IOException) {

        }
    }


    /**
     * check external storage status
     */
    private fun isMediaMounted(): Boolean {
        // 현재 단말기의 외부 저장소 상태를 받아옵니다
        val state = Environment.getExternalStorageState()
        Log.e("isMediaMounted", state)
        // 외부 저장소가 있을 경우 true를 리턴받습니다
        return state == Environment.MEDIA_MOUNTED
    }

    /**
     * read image(bitmap) file external storage
     */
    fun readExternalBitmap(context: Context, FILE_NAME: String): Bitmap? {
        if (!isMediaMounted()) {
            return null
        }
        val file = File(context.externalCacheDir, FILE_NAME)
        var bitmap: Bitmap? = null

        try {
            val fis = FileInputStream(file)
            bitmap = BitmapFactory.decodeStream(fis)
            fis.close()
        } catch (e: IOException) {

        }

        return bitmap
    }

    /**
     * write image(bitmap) at external storage
     */
    fun writeBitmapExternal(context: Context, FILE_NAME: String, bitmap: Bitmap) {
        if (!isMediaMounted()) {
            return
        }
        val file = File(context.externalCacheDir, FILE_NAME)
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()
        } catch (e: IOException) {

        }
    }


    /**
     * write file at external storage by FileWriter with separate directory
     */
    fun writeFileExternal(context: Context, DIR_NAME: String, FILE_NAME: String, content: String) {
        // 외부 저장소가 없을 경우 파일을 저장할 수 없습니다.
        if (!isMediaMounted()) {
            return
        }
        val fileWriter: FileWriter
        // 외부 저장 공간 root 하위에 넘겨준 DIR_NAME으로 디렉토리 생성
        val dirPath = Environment
                .getExternalStorageDirectory()
                .absolutePath + "/" + DIR_NAME
        try {
            // 폴더가 없을시 폴더 생성합니다
            val dir = File(dirPath)
            if (!dir.exists()) {
                dir.mkdir()
            }
            // 파일이 없을시 파일 생성합니다
            val file = File(dirPath + "/" + FILE_NAME)
            if (!file.exists()) {
                file.createNewFile()
            }
            // FileWriter를 통해 스트림을 열어줍니다
            fileWriter = FileWriter(FILE_NAME)
            // 파일 쓰기
            fileWriter.write(content)
            fileWriter.flush()
            // 스트림을 사용한 후 반드시 닫아줍니다
            fileWriter.close()
        } catch (e: IOException) {

        }
    }

    /**
     * write file at external storage by FileWriter with separate directory
     */
    fun writePublicExternal(context: Context, FILE_NAME: String, content: String) {
        val fileWriter: FileWriter
        // 외부 저장 공간 root 하위에 넘겨준 DIR_NAME으로 디렉토리 생성
        val dirPath = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .absolutePath
        try {
            // 폴더가 없을시 폴더 생성합니다
            val dir = File(dirPath, FILE_NAME)
            if (!dir.exists()) {
                dir.mkdir()
            }
            // 파일이 없을시 파일 생성합니다
            val file = File(dirPath + FILE_NAME)
            if (!file.exists()) {
                file.createNewFile()
            }
            // FileWriter를 통해 스트림을 열어줍니다
            fileWriter = FileWriter(FILE_NAME)
            fileWriter.write(content)
            fileWriter.flush()
            fileWriter.close()
        } catch (e: IOException) {

        }
    }

    /**
     * read file from other package, designed to share outside
     */
    fun readExternalPackageFile(context: Context, FILE_NAME: String) {
        var data: ByteArray? = null
        try {
            val packageContext = context.createPackageContext(
                    "database.kotlin.flow9.net.filebasic",
                    Context.CONTEXT_IGNORE_SECURITY)
            val fis = packageContext.openFileInput(FILE_NAME)
            data = ByteArray(fis.available())
            while (fis.read(data) != -1)
                fis.close()
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: IOException) {

        }

        Log.e("readExternalPackageFile", String(data!!))
    }

    /**
     * FileObserver
     */
    class ScreenCaptureObserver(path: String) : FileObserver(path) {

        override fun onEvent(event: Int, path: String?) {
            when (event) {
                FileObserver.CREATE -> Log.d("FileObserver.CREATE", "create : " + path!!)
                FileObserver.CLOSE_WRITE -> Log.d("FileObserver.END", "end : " + path!!)
            }
        }
    }


}