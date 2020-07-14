package misc

import android.content.Context
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Misc {

    companion object Misc {

        /*
            Given a context and an URI, return a path to a file that
            has the same content as the URI. The method creates a new
            file in dataDir during its execution.

            @param context - Context of the application
            @param URI     - URI

            @return - absolute path to a file with the same content as the URI
         */

        fun uriToPath (context: Context, uri: Uri): String? {

            val contentResolver = context.contentResolver ?: return null

            val filePath = context.applicationInfo.dataDir + File.separator +
                    System.currentTimeMillis()

            val file = File(filePath)

            try {

                val inputStream = contentResolver.openInputStream(uri) ?: return null
                val outputStream = FileOutputStream(file)

                val buffer = ByteArray(1024)

                while (true) {

                    val readBytes = inputStream.read(buffer)
                    if (readBytes <= 0)
                        break

                    outputStream.write(buffer, 0, readBytes)

                }

                inputStream.close()
                outputStream.close()

            } catch (e: IOException) {

                e.message?.let { Log.e("IOException", it) }
                return null

            }

            return file.absolutePath

        }

    }

}