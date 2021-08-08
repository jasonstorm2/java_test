package DesignModule.Builder;

/**
 * ������������Ϊ���ɱ䡣
 *
 * �ڲ���builder���ⲿ���������ĿҪһֱ��builder������Ҳ����ֻ��Ϊfinal������builder�����ʼ��ʱ��������finials��ֵ
 */
public class CompanyClient {
    public final String companyName;
    public final String companyAddress;

    public final double companyRegfunds;
    public final String mPerson;
    public final String mType;


    //���췽��
    public CompanyClient() {
        this(new Builder());
    }

    //���췽��
    public CompanyClient(Builder builder){
        this.companyName = builder.companyName;
        this.companyAddress = builder.companyAddress;
        this.companyRegfunds = builder.companyRegfunds;
        this.mPerson = builder.person;
        this.mType = builder.type;
    }
    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public double getCompanyRegfunds() {
        return companyRegfunds;
    }

    public String getmPerson() {
        return mPerson;
    }

    public String getmType() {
        return mType;
    }



//    public Builder newBuilder() {
//        return new Builder(this);
//    }

    @Override
    public String toString() {
        return "CompanyClient{" +
                "companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyRegfunds=" + companyRegfunds +"ǧ��"+
                ", mPerson=" + mPerson +
                ", mType='" + mType + '\'' +
                '}';
    }
    /**
     *��̬�ڲ��� Builder
     */

    public static class Builder{
        public String companyName;
        public String companyAddress;
        public  double companyRegfunds;
        public  String person;
        public String type;
        //���췽��
        public Builder() {
        }
        //���췽��
//        Builder(CompanyClient companyClient){
//            this.companyName = companyClient.companyName;
//            this.companyAddress = companyClient.companyAddress;
//            this.companyRegfunds = companyClient.companyRegfunds;
//            this.person = companyClient.mPerson;
//            this.type = companyClient.mType;
//        }

        public Builder setCompanyName(String name) {
            companyName = name;
            return this;
        }

        public Builder setCompanyAddress(String address) {
            companyAddress = address;
            return this;
        }

        public Builder setCompanyRegfunds(double regfunds) {
            companyRegfunds = regfunds;
            return this;
        }

        public Builder setmPerson(String per) {
            person = per;
            return this;
        }

        public Builder setmType(String typeStr) {
            type = typeStr;
            return this;
        }
        //����һ��ʵ��
        public CompanyClient build() {
            return new CompanyClient(this);
        }
    }
}