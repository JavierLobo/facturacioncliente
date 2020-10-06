package com.javierlobo.clientes.rest.auth;

public class JwtConfig {

	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";

	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEA6PIX03fEgaLdxzGPyxWN6dHo8MqIfCh8hQfUotokwLDgci2s\r\n" + 
			"fek9ONbvb5ugUfNaaq9RcO207L/Ml2fweJKE1w3nnlFP9PRMKSWQcciBJQFwFpKQ\r\n" + 
			"JYQ65WGsm0smImYooOuFTE4vt7ktRxaJhRxnyxz9ekFTOSRMV93i7q8dD77CxaET\r\n" + 
			"gtBIch6/yLw5MAvV2Q5L0oLs7dm1cag5lgqDMzBYG+SGsD6xzntLQ2xXqwoWJMHz\r\n" + 
			"5kepeiaN1dyaPyZu0uP4F68Kujpd3+hlpGRMahGBUgMZhTzPR94S/e2JEwSvrwM6\r\n" + 
			"Yz1I4/xs6TMtvwB5vcXA1TY+8RyM+V2OPnyMzQIDAQABAoIBAQCFDIF/TxD12khB\r\n" + 
			"1ZUDsbP0sOgTYBrEj3tPoEFbIq2LQXtwu5Wh9O364Qiw/np7VDt4Y9poXdzQvFvm\r\n" + 
			"vn8i3szw6Ag67MZ2anJ1rfbqhlL9B+01d1DNnNDm4vrrV+jYDPF3uA3Sn3WxwhqS\r\n" + 
			"V9UEKk0+Pbipc1ZNdOYF6b4MBgjbTEqM82hab0uxX+OrkYFbgBSqQOMM6h4pSxN5\r\n" + 
			"AnRVjrGT/W1kByTnanUgZ27gFLznDmrlLUIS4CUcvA3RiTd3UqHQpYTFlWSL4sjq\r\n" + 
			"lpsEEv8cqQfVqGlJS1cTA0Ar6Q9TVEexLc/0Woi2mVgQmpWbDLAmDoPZNC/WdOTr\r\n" + 
			"Za4WsfUBAoGBAPUs0wqfLTJUVpN3Aqi1Fj9CBO+IaHHe4UwchIDTra8wgD/V5fu+\r\n" + 
			"MEDSIHzI5PJI0V2ma6nKkHX6xYw+KUp9XH5IPFyRDulMbay/UIA+R5CZijWpEKPm\r\n" + 
			"JpCZ8ru2LBQvycbfLVdL3lNDvuerNEUfXNhzIoZ+PvOReisEdAM/8Af5AoGBAPM7\r\n" + 
			"Cp60+Dte+YtGzToqFZgNH3uZnsEtN8prXfQ7T7P8PRRg/cU5Z+uFtOxM37kDYcvk\r\n" + 
			"Ufjp930NdZ2KMWrIAv88o6GT5+YcnFkO3qfiNsFJxUEN/98KdLsbYhmf/6SDIVaH\r\n" + 
			"I7w7Bshs9ekwTKreivzOjwajZjCL3UjzeNMvWih1AoGAFcml94utuItGJlStJ9Dv\r\n" + 
			"6S80wRSeM4BbBSiDne8xwB/PRdz8UyLTpu8FdBFmRyGxeWEQsEYQEGv4oj3j7Xq2\r\n" + 
			"fYJt2swJRzJDjNZzkPH36iG65onfsLEDPFZx+7x0rlaQ19t9bASR/VRH4s6UFpBy\r\n" + 
			"78lLiCgv7/U9phT/Okq0SgECgYEAwBaXq4EGLXUzJJA00cOcrClo/Kb0nmEEl4K1\r\n" + 
			"f4U7y8vfvPFgWNqePH2qxZdsc+O831YgerDjuEnh4hnaLxdoboRIQfnaAXwE6bx5\r\n" + 
			"kSJw1bNZ6aeRtGbAi2fz0ILwVM8OjBRL34LKN4z9pDx1v9fi8gZ7I+X1dU9v4gYO\r\n" + 
			"HNJfpGUCgYEAk2VXwAf7CO0G9jlAZlEGurXgRWDNz8ID/GeKeGugRW0eVKrZluqU\r\n" + 
			"zvgK8nFN/sOT08mGmSY0nXKoiBy4huEh5qqaeBq11ZhZA76p6hSemDskLBysXqw7\r\n" + 
			"xtqvmDv1PsICE8kjQPDZPUIo+sxQv9NiMqcqqaZ1f8Xbe1rHeYtwNfA=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6PIX03fEgaLdxzGPyxWN\r\n" + 
			"6dHo8MqIfCh8hQfUotokwLDgci2sfek9ONbvb5ugUfNaaq9RcO207L/Ml2fweJKE\r\n" + 
			"1w3nnlFP9PRMKSWQcciBJQFwFpKQJYQ65WGsm0smImYooOuFTE4vt7ktRxaJhRxn\r\n" + 
			"yxz9ekFTOSRMV93i7q8dD77CxaETgtBIch6/yLw5MAvV2Q5L0oLs7dm1cag5lgqD\r\n" + 
			"MzBYG+SGsD6xzntLQ2xXqwoWJMHz5kepeiaN1dyaPyZu0uP4F68Kujpd3+hlpGRM\r\n" + 
			"ahGBUgMZhTzPR94S/e2JEwSvrwM6Yz1I4/xs6TMtvwB5vcXA1TY+8RyM+V2OPnyM\r\n" + 
			"zQIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
