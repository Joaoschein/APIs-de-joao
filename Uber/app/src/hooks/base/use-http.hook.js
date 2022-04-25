import { useAxios } from "./use-axios.hook";

export function useHttp(baseURL) {
  const instance = useAxios(baseURL);

  async function get(url) {
    try {
      const response = await instance.get(url);

      return response.data;
    } catch (error) {
      console.log(error);

      if (error.response.status === 401) {
        localStorage.removeItem("user");

        throw error;
      }
    }
  }

  async function post(url, data) {
    const response = await instance.post(url, data);

    return response.data;
  }

  async function put(url, data) {
    const response = await instance.put(url, data);

    return response.data;
  }

  return {
    get,
    post,
    put
  };
}