package database.kotlin.flow9.net.filebasic

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {

    // 체크할 퍼미션
    private val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val REQ_CODE = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun postPermissionGranted() {
        // 파일 입출력 코드
    }

    fun checkVersion() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            postPermissionGranted()
        } else {
            checkAlreadyGrantedPermission()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun checkAlreadyGrantedPermission() {
        val isAllGranted = permissions.none { checkSelfPermission(it) == PackageManager.PERMISSION_DENIED }
        if (isAllGranted) {
            postPermissionGranted()
        } else {
            requestPermissions(permissions, REQ_CODE)
        }
    }

    @Override
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val isAllGranted = grantResults.none { it == PackageManager.PERMISSION_DENIED }
        if (isAllGranted) {
            postPermissionGranted()
        } else {
            showAskAgainDialog()
        }
    }

    private fun showAskAgainDialog() {
        val dialog = AlertDialog.Builder(this).setTitle("권한 설정 필요").setMessage("현재 기능을 사용하기 위해서는 파일 읽기, 쓰기 권한이 필요합니다. 설정 페이지로 넘어가시겠습니까?")
                .setPositiveButton("예", {_, _ -> goSettings()})
                .setNegativeButton("아니오", null)
                .create()
        dialog.show()
    }

    private fun goSettings() {
        val intent =  Intent(Settings.ACTION_APPLICATION_SETTINGS, Uri.fromParts("package", packageName, null))
        startActivity(intent)
    }


}
