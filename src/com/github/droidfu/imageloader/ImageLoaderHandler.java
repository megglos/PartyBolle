/* Copyright (c) 2009 Matthias Käppler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.droidfu.imageloader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ImageLoaderHandler extends Handler implements ImageLoaderHandlerIF {

    private ImageView imageView;
    public ImageLoaderHandler(ImageView imageView) {
        this.imageView = imageView;
    }

    /* (non-Javadoc)
	 * @see com.github.droidfu.imageloader.ImageLoaderHandlerIF#handleMessage(android.os.Message)
	 */
    @Override
    public void handleMessage(Message msg) {
        if (msg.what == ImageLoader.HANDLER_MESSAGE_ID) {
            Bundle data = msg.getData();
            Bitmap bitmap = data.getParcelable(ImageLoader.BITMAP_EXTRA);
            imageView.setImageBitmap(bitmap);
        }
    }

    ImageView getImageView() {
        return imageView;
    }

	public Handler getHandler() {
		return this;
	}

}
