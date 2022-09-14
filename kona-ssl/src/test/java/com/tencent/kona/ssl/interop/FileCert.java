/*
 * Copyright (C) 2022 THL A29 Limited, a Tencent company. All rights reserved.
 * DO NOT ALTER OR REMOVE NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation. THL A29 Limited designates
 * this particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License version 2 for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.tencent.kona.ssl.interop;

import com.tencent.kona.ssl.TestUtils;

import java.nio.file.Path;

/**
 * The certificates and corresponding private keys from a file.
 */
public class FileCert extends Cert {

    private final Path certFilePath;
    private final Path keyFilePath;

    public FileCert(KeyAlgorithm keyAlgo,
                    SignatureAlgorithm sigAlgo,
                    HashAlgorithm hashAlgo,
                    String certDirName,
                    String certFileName,
                    String keyFileName) {
        super(keyAlgo, sigAlgo, hashAlgo,
                TestUtils.certStr(certDirName, certFileName),
                TestUtils.keyStr(certDirName, keyFileName));

        this.certFilePath = TestUtils.certFilePath(certDirName, certFileName);
        this.keyFilePath = TestUtils.certFilePath(certDirName, keyFileName);
    }

    // The default cert directory is test/resources/certs
    public FileCert(KeyAlgorithm keyAlgo,
                    SignatureAlgorithm sigAlgo,
                    HashAlgorithm hashAlgo,
                    String certFileName,
                    String keyFileName) {
        this(keyAlgo, sigAlgo, hashAlgo, "certs", certFileName, keyFileName);
    }

    public String certPath() {
        return certFilePath.toAbsolutePath().toString();
    }

    public String keyPath() {
        return keyFilePath.toAbsolutePath().toString();
    }
}
