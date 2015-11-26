package edu.stevens.cs548.clinic.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-09T22:07:55.490-0400")
@StaticMetamodel(Provider.class)
public class Provider_ {
	public static volatile SingularAttribute<Provider, Long> id;
	public static volatile SingularAttribute<Provider, String> providername;
	public static volatile SingularAttribute<Provider, Long> providerid;
	public static volatile SingularAttribute<Provider, String> specialization;
	public static volatile ListAttribute<Provider, Treatment> treatments;
}
