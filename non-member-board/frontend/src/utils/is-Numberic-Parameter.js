/**
 * 전달 받은 [parameter]의 값이 숫자인지 체크하는 메서드
 * return: true/false
 */
export const isNumbericParameter = (parameter) => {
  // 0~9까지의 숫자값 or 빈값X 정규식
  const regex = /^[0-9]+$/;

  return regex.test(parameter);
};
