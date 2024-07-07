export const getFormatDate = (targetDate) => {
  const date = new Date(String(targetDate));

  // string -> YYYY-MM-DD
  let year = addLeadingZero(date.getFullYear()); //년
  let month = addLeadingZero(date.getMonth() + 1); //월
  let day = addLeadingZero(date.getDate()); //일
  let hour = addLeadingZero(date.getHours()); //시
  let minute = addLeadingZero(date.getMinutes()); //분
  let second = addLeadingZero(date.getSeconds()); //초

  return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
};

const addLeadingZero = (string) => {
  return 10 > Number(string) ? `0${Number(string)}` : string;
};
