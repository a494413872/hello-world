package com.lvmama.pdfGenerator;


    public enum ImageCode{
        QR("QRCODE"),BARA("128A");
        private String code;
        ImageCode(String code){
            this.code=code;
        }
        public String getCode(){
            return this.code;
        }
        public static void printAll(){
            for(ImageCode c:ImageCode.values()){
                System.out.println(c.getCode());
            }
        }

    }

